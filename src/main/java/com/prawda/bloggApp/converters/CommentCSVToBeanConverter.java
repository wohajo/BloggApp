package com.prawda.bloggApp.converters;

import com.opencsv.bean.CsvToBeanBuilder;
import com.prawda.bloggApp.domain.Comment;
import lombok.RequiredArgsConstructor;

import java.io.*;
import java.nio.file.Files;
import java.util.List;

@RequiredArgsConstructor
public class CommentCSVToBeanConverter {

    public void parse() throws IOException {
        String fileName = "src/main/resources/Comments.csv";
        String outputFileName = "src/main/resources/Comments.xml";

        List<Comment> beans = new CsvToBeanBuilder(new FileReader(fileName))
                .withType(Comment.class).build().parse();

        File newFile = new File(outputFileName);
        Files.deleteIfExists(newFile.toPath());
        newFile.createNewFile();

        FileWriter writer = new FileWriter(outputFileName, true);
        BufferedWriter bufferedWriter = new BufferedWriter(writer);

        String header =
                "<?xml version=\"1.0\" encoding=\"UTF-8\"?>\n"
                        + " <beans xmlns=\"http://www.springframework.org/schema/beans\"\n"
                        + " xmlns:xsi=\"http://www.w3.org/2001/XMLSchema-instance\"\n"
                        + " xsi:schemaLocation=\"http://www.springframework.org/schema/beans http://www.springframework.org/schema/beans/spring-beans.xsd\">\n";

        String footer = "\n</beans>";


        bufferedWriter.write(header);


        for (Comment bean: beans) {
            String beanXML =
                    "   <bean id=\"comment" + bean.getId() + "\" class=\"com.prawda.bloggApp.domain.Comment\">\n"
                    + "     <constructor-arg name=\"id\" value=\"" + bean.getId() + "\"/>\n"
                    + "     <constructor-arg name=\"username\" value=\"" + bean.getUsername() + "\"/>\n"
                    + "     <constructor-arg name=\"postId\" value=\"" + bean.getPostId() + "\"/>\n"
                    + "     <constructor-arg name=\"contents\" value=\"" + bean.getContents() + "\"/>\n"
                    + "     </bean>\n";
            bufferedWriter.write(beanXML);
        }

        bufferedWriter.write(footer);
        bufferedWriter.close();
    }
}
