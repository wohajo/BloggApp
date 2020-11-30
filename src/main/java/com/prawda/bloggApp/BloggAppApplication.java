package com.prawda.bloggApp;

import com.prawda.bloggApp.converters.CommentCSVToBeanConverter;
import com.prawda.bloggApp.converters.ManyPostsManyAuthorsConverter;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
public class BloggAppApplication {

	public static void main(String[] args) throws Exception {
		CommentCSVToBeanConverter commentCsvToBeanConverter = new CommentCSVToBeanConverter();
		ManyPostsManyAuthorsConverter manyPostsManyAuthorsConverter = new ManyPostsManyAuthorsConverter();
		commentCsvToBeanConverter.test();
		manyPostsManyAuthorsConverter.readLineByLine();
		SpringApplication.run(BloggAppApplication.class, args);
	}

}
