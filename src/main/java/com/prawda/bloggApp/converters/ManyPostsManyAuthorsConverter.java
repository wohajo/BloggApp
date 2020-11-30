package com.prawda.bloggApp.converters;

import com.opencsv.CSVReader;
import lombok.RequiredArgsConstructor;

import java.io.BufferedWriter;
import java.io.File;
import java.io.FileWriter;
import java.io.Reader;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

@RequiredArgsConstructor
public class ManyPostsManyAuthorsConverter {

    public void readLineByLine() throws Exception {
        Reader reader = Files.newBufferedReader(Paths.get(
                ClassLoader.getSystemResource("ManyPostsManyAuthors.csv").toURI()));
        readLines(reader);
    }

    public List<String[]> readLines(Reader reader) throws Exception {
        List<String[]> list = new ArrayList<>();
        CSVReader csvReader = new CSVReader(reader);
        String[] line;
        String outputFileName = "src/main/resources/ManyPostsManyAuthors.xml";
        String header =
                "<?xml version=\"1.0\" encoding=\"UTF-8\"?>\n"
                        + " <beans xmlns=\"http://www.springframework.org/schema/beans\"\n"
                        + " xmlns:xsi=\"http://www.w3.org/2001/XMLSchema-instance\"\n"
                        + " xsi:schemaLocation=\"http://www.springframework.org/schema/beans http://www.springframework.org/schema/beans/spring-beans.xsd\">\n";

        String footer = "\n</beans>";

        File newFile = new File(outputFileName);
        Files.deleteIfExists(newFile.toPath());
        newFile.createNewFile();

        FileWriter writer = new FileWriter(outputFileName, true);
        BufferedWriter bufferedWriter = new BufferedWriter(writer);
        bufferedWriter.write(header);

        while ((line = csvReader.readNext()) != null) {
            list.add(line);
            System.out.println(Arrays.stream(line).toArray()[0]);
            System.out.println(Arrays.stream(line).toArray()[1]);
            System.out.println(Arrays.stream(line).toArray()[2]);
            System.out.println(Arrays.stream(line).toArray()[3]);
        }

        bufferedWriter.write(footer);

        reader.close();
        csvReader.close();
        return list;
    }
}
