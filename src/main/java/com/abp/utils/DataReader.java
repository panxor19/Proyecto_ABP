package com.abp.utils;

import org.apache.commons.csv.CSVFormat;
import org.apache.commons.csv.CSVParser;
import org.apache.commons.csv.CSVRecord;
import org.apache.poi.ss.usermodel.*;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;

import java.io.FileReader;
import java.io.IOException;
import java.io.InputStream;
import java.util.ArrayList;
import java.util.List;

/**
 * Utilidad para leer datos de prueba desde archivos CSV y Excel
 */
public class DataReader {
    
    /**
     * Lee datos de usuario desde un archivo CSV para ParaBank
     * @param filePath Ruta del archivo CSV
     * @return Lista de arrays de objetos con los datos de usuario
     */
    public static Object[][] readUserDataFromCSV(String filePath) {
        List<Object[]> data = new ArrayList<>();
        
        try (FileReader reader = new FileReader(filePath);
             CSVParser parser = new CSVParser(reader, CSVFormat.DEFAULT.withFirstRecordAsHeader())) {
            
            for (CSVRecord record : parser) {
                String username = record.get("username");
                String password = record.get("password");
                String expectedResult = record.get("expectedResult");
                
                data.add(new Object[]{username, password, expectedResult});
            }
            
        } catch (IOException e) {
            System.err.println("Error leyendo archivo CSV: " + e.getMessage());
        }
        
        return data.toArray(new Object[0][]);
    }
    
    /**
     * Lee datos de registro desde un archivo CSV para ParaBank
     * @param filePath Ruta del archivo CSV
     * @return Lista de arrays de objetos con los datos de registro
     */
    public static Object[][] readRegistrationDataFromCSV(String filePath) {
        List<Object[]> data = new ArrayList<>();
        
        try (FileReader reader = new FileReader(filePath);
             CSVParser parser = new CSVParser(reader, CSVFormat.DEFAULT.withFirstRecordAsHeader())) {
            
            for (CSVRecord record : parser) {
                String firstName = record.get("firstName");
                String lastName = record.get("lastName");
                String address = record.get("address");
                String city = record.get("city");
                String state = record.get("state");
                String zipCode = record.get("zipCode");
                String phone = record.get("phone");
                String ssn = record.get("ssn");
                String username = record.get("username");
                String password = record.get("password");
                String confirmPassword = record.get("confirmPassword");
                String expectedResult = record.get("expectedResult");
                
                data.add(new Object[]{firstName, lastName, address, city, state, zipCode, 
                                    phone, ssn, username, password, confirmPassword, expectedResult});
            }
            
        } catch (IOException e) {
            System.err.println("Error leyendo archivo CSV: " + e.getMessage());
        }
        
        return data.toArray(new Object[0][]);
    }
    
    /**
     * Lee datos desde un archivo Excel
     * @param filePath Ruta del archivo Excel
     * @param sheetName Nombre de la hoja
     * @return Lista de arrays de objetos con los datos
     */
    public static Object[][] readDataFromExcel(String filePath, String sheetName) {
        List<Object[]> data = new ArrayList<>();
        
        try (InputStream inputStream = DataReader.class.getClassLoader().getResourceAsStream(filePath);
             Workbook workbook = new XSSFWorkbook(inputStream)) {
            
            Sheet sheet = workbook.getSheet(sheetName);
            if (sheet == null) {
                System.err.println("Hoja no encontrada: " + sheetName);
                return new Object[0][];
            }
            
            // Obtener headers desde la primera fila
            Row headerRow = sheet.getRow(0);
            int columnCount = headerRow.getLastCellNum();
            
            // Leer datos desde la segunda fila
            for (int i = 1; i <= sheet.getLastRowNum(); i++) {
                Row row = sheet.getRow(i);
                if (row != null) {
                    Object[] rowData = new Object[columnCount];
                    for (int j = 0; j < columnCount; j++) {
                        Cell cell = row.getCell(j);
                        if (cell != null) {
                            switch (cell.getCellType()) {
                                case STRING:
                                    rowData[j] = cell.getStringCellValue();
                                    break;
                                case NUMERIC:
                                    rowData[j] = String.valueOf((long) cell.getNumericCellValue());
                                    break;
                                case BOOLEAN:
                                    rowData[j] = String.valueOf(cell.getBooleanCellValue());
                                    break;
                                default:
                                    rowData[j] = "";
                            }
                        } else {
                            rowData[j] = "";
                        }
                    }
                    data.add(rowData);
                }
            }
            
        } catch (IOException e) {
            System.err.println("Error leyendo archivo Excel: " + e.getMessage());
        }
        
        return data.toArray(new Object[0][]);
    }
    
    /**
     * Proporciona datos de login hardcodeados para pruebas rápidas
     */
    public static Object[][] getLoginTestData() {
        return new Object[][] {
            {"usuario_valido@test.com", "Password123!", "success"},
            {"usuario_invalido@test.com", "wrongpassword", "error"},
            {"", "Password123!", "error"},
            {"usuario_valido@test.com", "", "error"},
            {"email_invalido", "Password123!", "error"},
            {"usuario_bloqueado@test.com", "Password123!", "blocked"}
        };
    }
    
    /**
     * Proporciona datos de registro hardcodeados para pruebas rápidas
     */
    public static Object[][] getRegistrationTestData() {
        return new Object[][] {
            {"Juan", "Pérez", "juan.perez@test.com", "Password123!", "Password123!", "555-1234", "success"},
            {"", "García", "maria.garcia@test.com", "Password123!", "Password123!", "555-5678", "error"},
            {"Pedro", "", "pedro.lopez@test.com", "Password123!", "Password123!", "555-9012", "error"},
            {"Ana", "Martín", "email_invalido", "Password123!", "Password123!", "555-3456", "error"},
            {"Luis", "Rodríguez", "luis.rodriguez@test.com", "123", "123", "555-7890", "error"},
            {"Carmen", "Fernández", "carmen.fernandez@test.com", "Password123!", "DifferentPassword", "555-2468", "error"}
        };
    }
}
