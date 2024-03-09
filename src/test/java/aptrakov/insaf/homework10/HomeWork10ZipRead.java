package aptrakov.insaf.homework10;


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
                if (entry.getName().endsWith(".csv")) {
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
                if (entry.getName().endsWith(".pdf")) {
                    PDF pdf = new PDF(zis);
                    Assertions.assertTrue(pdf.text.contains("TestInsaf"));
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
                if (entry.getName().endsWith(".xlsx")) {
                    XLS xlsx = new XLS(zis);
                    Assertions.assertEquals("testxlsx.xlsx",
                            xlsx.excel.getSheet("TestInsaf").getRow(1).getCell(0).getStringCellValue());
                }

            }
        }
    }

    @Test
    @DisplayName("Парсинг JSON файла ")
    void jsonParsingTest() throws Exception {
        try (InputStream is = cl.getResourceAsStream("jsonfile.json");
             Reader reader = new InputStreamReader(is)) {
            JsonObject object = gson.fromJson(reader, JsonObject.class);

            Assertions.assertEquals("Insaf", object.get("name").getAsString());
            Assertions.assertEquals(27, object.get("age").getAsInt());
            Assertions.assertArrayEquals(
                    new String[]{"Insaf", "Aptrakov"},
                    object.get("fullName").getAsJsonArray()
                            .asList()
                            .stream()
                            .map(JsonElement::getAsString)
                            .toArray()
            );
        }
    }
}
