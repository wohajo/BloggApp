package com.prawda.bloggApp;

import com.opencsv.exceptions.CsvValidationException;
import com.prawda.bloggApp.converters.CSVToBeanConverter;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

import java.io.IOException;

@SpringBootApplication
public class BloggAppApplication {

	public static void main(String[] args) throws IOException, CsvValidationException {
		CSVToBeanConverter csvToBeanConverter = new CSVToBeanConverter();
		csvToBeanConverter.test();
		SpringApplication.run(BloggAppApplication.class, args);
	}

}
