package com.kinteg.testjdbc;

import com.kinteg.testjdbc.model.Buyer;
import com.kinteg.testjdbc.repo.BuyerRepo;
import com.kinteg.testjdbc.repo.jdbc.BuyerRepoImpl;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.ApplicationContext;
import org.springframework.context.ConfigurableApplicationContext;

@SpringBootApplication
public class TestjdbcApplication {

    public static void main(String[] args) {
        ConfigurableApplicationContext context = SpringApplication.run(TestjdbcApplication.class, args);

        BuyerRepo repo = context.getBean(BuyerRepoImpl.class);

        repo.save(new Buyer(1L, "Vova1", "Rus", 324327854)).ifPresent(System.out::println);
        repo.save(new Buyer(2L, "Vova2", "Rus", 324327854)).ifPresent(System.out::println);
        repo.save(new Buyer(3L, "Vova3", "Rus", 324327854)).ifPresent(System.out::println);
        repo.save(new Buyer(4L, "Vova4", "Rus", 324327854)).ifPresent(System.out::println);
        repo.save(new Buyer(5L, "Vova5", "Rus", 324327854)).ifPresent(System.out::println);

        try {
            repo.failSave(new Buyer(6L, "Vova6", "Rus", 324327854)).ifPresent(System.out::println);
        } catch (RuntimeException ignored) {}

        System.out.println("-------------------------------------------------------------------------------------");
        repo.findById(1L).ifPresent(System.out::println);
        repo.findById(2L).ifPresent(System.out::println);
        repo.findById(3L).ifPresent(System.out::println);
        repo.findById(4L).ifPresent(System.out::println);
        repo.findById(5L).ifPresent(System.out::println);
        repo.findById(6L).ifPresent(System.out::println);
        System.out.println("-------------------------------------------------------------------------------------");
        repo.findAll().forEach(System.out::println);

        context.close();
    }

}
