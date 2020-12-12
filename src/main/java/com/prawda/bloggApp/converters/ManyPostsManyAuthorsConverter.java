package com.prawda.bloggApp.converters;

import com.opencsv.CSVReader;
import com.opencsv.CSVReaderBuilder;
import lombok.RequiredArgsConstructor;

import java.io.*;
import java.nio.file.Files;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

@RequiredArgsConstructor
public class ManyPostsManyAuthorsConverter {

    public List<String> getAuthorsFromString (String authors) {
        return Arrays.asList(authors
                .replaceAll("\\[*\\]*\\{*\\}*", "")
                .replaceAll("\"*,*", "")
                .replaceAll(",", " ")
                .replaceAll("authors", "")
                .replaceFirst(":", "")
                .split(":"));
    }

    public List<String> getTagsFromString (String tags) {
        return  Arrays.asList(tags.split(" "));
    }

    public List<String[]> parse() throws Exception {
        List<String[]> list = new ArrayList<>();
        CSVReader csvReader = new CSVReaderBuilder(new FileReader("src/main/resources/ManyPostsManyAuthors.csv"))
                .withSkipLines(1).build();
        String[] line;
        String outputFileName = "src/main/resources/ManyPostsManyAuthors.xml";
        String header = "<?xml version=\"1.0\" encoding=\"UTF-8\"?>\n" +
                "<beans xmlns=\"http://www.springframework.org/schema/beans\"\n" +
                "        xmlns:xsi=\"http://www.w3.org/2001/XMLSchema-instance\"\n" +
                "        xmlns:util=\"http://www.springframework.org/schema/util\"\n" +
                "        xsi:schemaLocation=\"http://www.springframework.org/schema/beans\n" +
                "            http://www.springframework.org/schema/beans/spring-beans.xsd\n" +
                "            http://www.springframework.org/schema/util\n" +
                "            http://www.springframework.org/schema/util/spring-util.xsd\"\n>";

        String footer = "\n</beans>";

        File newFile = new File(outputFileName);
        Files.deleteIfExists(newFile.toPath());
        newFile.createNewFile();

        FileWriter writer = new FileWriter(outputFileName, true);
        BufferedWriter bufferedWriter = new BufferedWriter(writer);
        bufferedWriter.write(header);

        while ((line = csvReader.readNext()) != null) {
            list.add(line);
            String id = (String) Arrays.stream(line).toArray()[0];
            List<String> authorsList = getAuthorsFromString((String) Arrays.stream(line).toArray()[1]);
            List<String> tags = getTagsFromString((String) Arrays.stream(line).toArray()[3]);
            String contents = (String) Arrays.stream(line).toArray()[2];

            StringBuilder xml = new StringBuilder();
            xml.append("    <util:list id=\"list" + id + "\" value-type=\"java.lang.String\"  list-class=\"java.util.ArrayList\">\n");
            for (String fullName: authorsList) {
                xml.append("        <value>" + fullName + "</value>\n");
            }
            xml.append("    </util:list>\n");
            xml.append("    <util:list id=\"tags" + id + "\" value-type=\"java.lang.String\"  list-class=\"java.util.ArrayList\">\n");
            for (String tag: tags) {
                xml.append("        <value>" + tag + "</value>\n");
            }
            xml.append("    </util:list>\n");
            xml.append("    <bean id=\"post" + id + "\" class=\"com.prawda.bloggApp.domain.Post\">\n");
            xml.append("        <constructor-arg name=\"id\" value=\"" + id + "\"/>\n");
            xml.append("        <constructor-arg name=\"authors\" ref=\"list" + id + "\"/>\n");
            xml.append("        <constructor-arg name=\"tags\" ref=\"tags" + id + "\"/>\n");
            xml.append("        <constructor-arg name=\"contents\" value=\"" + contents + "\"/>\n");
            xml.append("    </bean>\n");
            bufferedWriter.write(xml.toString());
        }

        bufferedWriter.write(footer);
        bufferedWriter.close();
        csvReader.close();
        return list;
    }
}
