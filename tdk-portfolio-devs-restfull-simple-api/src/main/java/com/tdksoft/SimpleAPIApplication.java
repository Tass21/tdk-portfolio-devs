package com.tdksoft;

import com.tdksoft.api.Book;
import com.tdksoft.api.BookRepository;
import lombok.AllArgsConstructor;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;

import java.util.UUID;


@SpringBootApplication
@AllArgsConstructor
public class SimpleAPIApplication implements CommandLineRunner {

    private final BookRepository bookRepository;

    public static void main(String[] args) {
        SpringApplication.run(SimpleAPIApplication.class, args);
    }


    @Override
    public void run(String... args) throws Exception {
/*
       Book book = new Book("API made simple with Spring boot",
               "TDK Cooporation",
               "ISBN456",
               2023
               );
        bookRepository.save(book);*/
    }
}