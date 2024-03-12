package aptrakov.insaf.homework10;

import aptrakov.insaf.homework10.model.User;

import com.fasterxml.jackson.databind.ObjectMapper;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import com.google.gson.Gson;
import com.codeborne.pdftest.PDF;
import com.codeborne.xlstest.XLS;
import com.google.gson.JsonElement;
import com.google.gson.JsonObject;
import com.opencsv.CSVReader;
import org.junit.jupiter.api.Assertions;

import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.Reader;
import java.util.List;
import java.util.zip.ZipEntry;
import java.util.zip.ZipInputStream;


import static org.assertj.core.api.Assertions.assertThat;



public class HomeWork10ZipRead {
    private final ClassLoader cl = HomeWork10ZipRead.class.getClassLoader();
    private final Gson gson = new Gson();

    @Test
    @DisplayName("Чтение CSV файла ")
    void zipParsingTest() throws Exception {
        try (InputStream is = cl.getResourceAsStream("test.zip");
             ZipInputStream zis = new ZipInputStream(is)) {
            ZipEntry entry;
            while ((entry = zis.getNextEntry()) != null) {
                if (entry.getName().contains("testcsv.csv")) {
                    CSVReader csvReader = new CSVReader(new InputStreamReader(zis));
                    List<String[]> content = csvReader.readAll();
                    Assertions.assertArrayEquals(new String[]{"TestInsaf"}, content.get(0));
                }

            }
        }
    }

    @Test
    @DisplayName("Чтение PDF файла")
    void pdfParsingTest() throws Exception {
        try (InputStream is = cl.getResourceAsStream("test.zip");
             ZipInputStream zis = new ZipInputStream(is)) {
            ZipEntry entry;
            while ((entry = zis.getNextEntry()) != null) {
                if (entry.getName().contains("testpdf.pdf")) {
                    PDF pdf = new PDF(zis);
                    Assertions.assertTrue(pdf.text.contains("Схема взаимодействия"));
                }

            }
        }
    }

    @Test
    @DisplayName("Чтение XLSX файла")
    void xlsxParsingTest() throws Exception {
        try (InputStream is = cl.getResourceAsStream("test.zip");
             ZipInputStream zis = new ZipInputStream(is)) {
            ZipEntry entry;
            while ((entry = zis.getNextEntry()) != null) {
                if (entry.getName().contains("testxlsx.xls")) {
                    XLS xls = new XLS(zis);
                    assertThat(xls.excel.getSheet("Лист1").getRow(0).getCell(0).getStringCellValue()).isEqualTo("TestInsaf");
                }

            }
        }
    }


    @Test
    @DisplayName("Парсинг JSON файла ")
    void jsonParsingTest() throws Exception {
        try (InputStream is = cl.getResourceAsStream("jsonfile.json");
             Reader reader = new InputStreamReader(is)) {
            ObjectMapper objectMapper = new ObjectMapper();
            User user = objectMapper.readValue(reader, User.class);
            assertThat(user.getAge()).isEqualTo(27);
            assertThat(user.getName()).isEqualTo("Insaf");
            assertThat(user.getFullName().toArray()).isEqualTo(new String[]{"Insaf", "Aptrakov"});




        }
    }
}
