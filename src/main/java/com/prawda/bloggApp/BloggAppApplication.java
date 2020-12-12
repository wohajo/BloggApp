package com.prawda.bloggApp;

import com.prawda.bloggApp.converters.CommentCSVToBeanConverter;
import com.prawda.bloggApp.converters.ManyPostsManyAuthorsConverter;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.ApplicationContext;

@SpringBootApplication
public class BloggAppApplication {

	public static void main(String[] args) throws Exception {
		CommentCSVToBeanConverter commentCsvToBeanConverter = new CommentCSVToBeanConverter();
		ManyPostsManyAuthorsConverter manyPostsManyAuthorsConverter = new ManyPostsManyAuthorsConverter();
		commentCsvToBeanConverter.parse();
		manyPostsManyAuthorsConverter.parse();
		ApplicationContext context = SpringApplication.run(BloggAppApplication.class, args);
	}

}
