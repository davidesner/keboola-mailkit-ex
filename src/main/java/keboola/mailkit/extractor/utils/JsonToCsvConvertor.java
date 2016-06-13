/*
 */
package keboola.mailkit.extractor.utils;

import com.fasterxml.jackson.core.JsonFactory;
import com.fasterxml.jackson.core.JsonParser;
import com.fasterxml.jackson.core.JsonToken;
import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.MappingJsonFactory;
import com.fasterxml.jackson.databind.ObjectMapper;
import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.ByteArrayInputStream;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.FileWriter;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.nio.charset.Charset;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Iterator;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;
import java.util.logging.Level;
import java.util.logging.Logger;
import org.supercsv.cellprocessor.Optional;
import org.supercsv.cellprocessor.ift.CellProcessor;
import org.supercsv.io.CsvMapWriter;
import org.supercsv.prefs.CsvPreference;

/**
 * Convertor from JSON to CSV files.
 * The JSON file needs to have following structure:
 * [
 * {name:value, name:value, ..}
 * ]
 * Otherwise it wont be converted.
 *
 * @author David Esner <esnerda at gmail.com>
 * @created 2015
 */
public class JsonToCsvConvertor {
    
    private final CsvPreference csvPreference;
    private boolean headerLengthVaries;
    private boolean append;

    //build with default config
    public JsonToCsvConvertor() {
        this.csvPreference = CsvPreference.STANDARD_PREFERENCE;
        this.headerLengthVaries = false;
    }

    /**
     * Converts JSON file to CSV
     *
     * @param sourcePath path of the source json file
     * @param destPath path of the destination csv file
     * @throws Exception
     */
    public void convert(String sourcePath, String destPath) throws Exception {
        
        File source = new File(sourcePath);
        if (!source.exists()) {
            throw new Exception("File: " + sourcePath + " does not exist.");
        }
        File destination = new File(destPath);
        
        FileInputStream fis = null;
        fis = new FileInputStream(source);
        convert(fis, source, destination, null, null);
    }
    
    public void singleJsonObjectToCsv(String srcPath, String destPath, Map<String, String> colsToAdd, boolean append) throws ConvertException {
        CsvMapWriter mapWriter = null;
        
        File source = new File(srcPath);
        if (!source.exists()) {
            throw new ConvertException("File: " + srcPath + " does not exist.");
        }
        File dest = new File(destPath);
        if (append) {
            
            if (!dest.exists()) {
                append = false;
            }
        }
        try {
            ObjectMapper mapper = new ObjectMapper();
            Map<String, Object> resmap;
            resmap = mapper.readValue(source, HashMap.class);
            if (resmap.isEmpty()) {
                return;
            }
            if (colsToAdd != null && !colsToAdd.isEmpty()) {
                resmap.putAll(colsToAdd);
            }
            
            mapWriter = new CsvMapWriter(new BufferedWriter(new FileWriter(dest, append)),
                    CsvPreference.STANDARD_PREFERENCE);
            final String[] header = resmap.keySet().toArray(new String[0]);
            final CellProcessor[] processors = getProcessors(header.length);
            
            if (!append) {
                // write the header
                mapWriter.writeHeader(header);
            }
//write to file

            mapWriter.write(resmap, header, processors);
        } catch (IOException ex) {
            throw new ConvertException(ex.getLocalizedMessage());
        } finally {
            try {
                if (mapWriter != null) {
                    mapWriter.close();
                }
            } catch (IOException ex) {
                Logger.getLogger(JsonToCsvConvertor.class.getName()).log(Level.SEVERE, null, ex);
            }
        }
        
    }
    
    public void convertAndAdd(String sourcePath, String destPath, Map<String, String> colsToAdd, boolean append) throws ConvertException {
        this.append = append;
        File source = new File(sourcePath);
        if (!source.exists()) {
            throw new ConvertException("File: " + sourcePath + " does not exist.");
        }
        
        File destination = new File(destPath);
        FileInputStream is;
        try {
            is = new FileInputStream(source);
        } catch (FileNotFoundException ex) {
            throw new ConvertException(ex.getMessage());
        }
        
        convert(is, null, destination, colsToAdd, null);
    }
    
    public List<String> convert(String sourcePath, String destPath, String returnColName) throws ConvertException {
        
        File source = new File(sourcePath);
        if (!source.exists()) {
            throw new ConvertException("File: " + sourcePath + " does not exist.");
        }
        File destination = new File(destPath);
        
        FileInputStream is;
        try {
            is = new FileInputStream(source);
        } catch (FileNotFoundException ex) {
            throw new ConvertException(ex.getMessage());
        }
        return convert(is, source, destination, null, returnColName);
    }
    
    public List<String> convertAndAdd(String sourcePath, String destPath, Map<String, String> colsToAdd, boolean append, String returnColName) throws ConvertException {
        this.append = append;
        File source = new File(sourcePath);
        if (!source.exists()) {
            throw new ConvertException("File: " + sourcePath + " does not exist.");
        }
        
        File destination = new File(destPath);
        FileInputStream is;
        try {
            is = new FileInputStream(source);
        } catch (FileNotFoundException ex) {
            throw new ConvertException(ex.getMessage());
        }
        
        return convert(is, null, destination, colsToAdd, returnColName);
    }
    
    public void convertAndAddFromString(String data, String destPath, Map<String, String> colsToAdd, boolean append) throws ConvertException {
        this.append = append;
        this.headerLengthVaries = false;
        
        File destination = new File(destPath);
        InputStream is = new ByteArrayInputStream(data.getBytes());
        
        convert(is, null, destination, colsToAdd, null);
    }
    
    private List<String> convert(InputStream is, File source, File destination,
            Map<String, String> colsToAdd, String returnColName) throws ConvertException {
        
        if (!destination.exists()) {
            this.append = false;
        }
        
        CsvMapWriter writer = null;
        BufferedReader rd = null;
        FileOutputStream fos;
        
        List<String> returnCol = new ArrayList<>();
        
        try {
            
            JsonFactory f = new MappingJsonFactory();
            
            rd = new BufferedReader(new InputStreamReader(is, Charset.forName("UTF-8")));
            
            JsonParser jp = f.createParser(rd);
            JsonToken currentToken;
            
            boolean firstRun = true;
            currentToken = jp.nextToken();
            if (currentToken != JsonToken.START_ARRAY) {
                if (currentToken == JsonToken.START_OBJECT && jp.nextToken() == JsonToken.END_OBJECT) {
                    return returnCol;
                }
                System.out.println("Error: invalid JSON format.");
                throw new Exception("Error: invalid JSON format ");
            }
            currentToken = jp.nextToken();

            //retrieve all headers
            Map<String, String> lineData = new HashMap();
            if (headerLengthVaries) {
                lineData = getAllHeaders(source);
            }
            String[] header = null;
            CellProcessor[] processors = null;

            // write the header
            // For each of the records in the array
            int line = 1;
            try {
                fos = new FileOutputStream(destination, append);
            } catch (FileNotFoundException ex) {
                throw new ConvertException("Error writing file: " + destination.getName() + " " + ex.getMessage());
            }
            writer = new CsvMapWriter(new OutputStreamWriter(fos, Charset.forName("UTF-8")), csvPreference);
            while (currentToken != JsonToken.END_ARRAY) {
                line++;
                // read the record into a tree model,
                // this moves the parsing position to the end of it
                JsonNode node = jp.readValueAsTree();
                
                for (Iterator<String> fields = node.fieldNames(); fields.hasNext();) {
                    String field = fields.next();
                    lineData.put(field, node.get(field).asText());
                    //add columns
                    if (colsToAdd != null) {
                        lineData.putAll(colsToAdd);
                    }
                    
                }

                //writer to output file
                //write header if firstRun
                if (firstRun) {
                    header = lineData.keySet().toArray(new String[0]);
                    processors = getProcessors(header.length);
                    if (!append) {//only when not appending
                        writer.writeHeader(lineData.keySet().toArray(new String[0]));
                    }
                    firstRun = false;
                }
                //colect return data
                if (returnColName != null) {
                    if (lineData.get(returnColName) != null) {
                        returnCol.add(lineData.get(returnColName));
                    }
                }
                writer.write(lineData, header, processors);
                //reset the line
                for (Map.Entry<String, String> entry : lineData.entrySet()) {
                    entry.setValue(null);
                }
                currentToken = jp.nextToken();
            }
            return returnCol;
        } catch (IOException ex) {
            throw new ConvertException("Error writing file: " + destination.getName() + " " + ex.getMessage());
        } catch (Exception ex) {
            ex.printStackTrace();
            throw new ConvertException("Error convertingjson: " + ex.getMessage());
        } finally {
            try {
                writer.close();
                rd.close();
                is.close();
                
            } catch (IOException ex) {
                ex.printStackTrace();
            } catch (RuntimeException ex) {
                
            }
            
        }
        
    }

    /**
     * Iterates through whole file to find all possible headers
     *
     * @param jsonFile - json file to parse
     * @return Map of all possible headers as keys and empty values
     * @throws Exception
     */
    private Map<String, String> getAllHeaders(File jsonFile) throws Exception {
        
        Map<String, String> headers = new LinkedHashMap<String, String>();
        
        FileInputStream fis = null;
        BufferedReader rd = null;
        JsonParser jp = null;
        try {
            JsonFactory f = new MappingJsonFactory();
            fis = new FileInputStream(jsonFile);
            rd = new BufferedReader(new InputStreamReader(fis, Charset.forName("UTF-8")));
            
            jp = f.createParser(rd);
            JsonToken currentToken;
            jp.nextToken();
            currentToken = jp.nextToken();
            
            while (currentToken != JsonToken.END_ARRAY) {
                
                JsonNode node = jp.readValueAsTree();
                
                for (Iterator<String> fields = node.fieldNames(); fields.hasNext();) {
                    String field = fields.next();
                    //add header,if not already exist
                    headers.put(field, null);
                }
                currentToken = jp.nextToken();
            }
            
            return headers;
            
        } catch (FileNotFoundException ex) {
            throw new Exception("Error reading file: " + jsonFile.getName() + " " + ex.getMessage());
        } catch (IOException ex) {
            throw new Exception("Error reading file: " + jsonFile.getName() + " " + ex.getMessage());
        } finally {
            rd.close();
            fis.close();
            jp.close();
        }
    }

    /*get cell processors with dynamic size*/
    private static CellProcessor[] getProcessors(int length) {
        CellProcessor[] processors = new CellProcessor[length];
        for (int i = 0; i < length; i++) {
            processors[i] = new Optional();
            
        }
        
        return processors;
        
    }
    
    public static class ConvertException extends Exception {
        
        public ConvertException(String message) {
            super(message);
        }
        
    }
}
