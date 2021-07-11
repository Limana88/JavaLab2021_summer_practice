package ru.itis;

import ru.itis.InterfacesRepo.CourseRepository;
import ru.itis.Repositories.CourseRepositoryImpl;

import java.io.IOException;
import java.util.Properties;

public class Main {

    public static void main(String[] args) {
        Properties properties = new Properties();
        try {
            properties.load(ClassLoader.getSystemResourceAsStream("application.properties"));
        } catch (IOException e) {
           throw new IllegalArgumentException(e);
        }
        JdbcPooledDataSourceFactory dataSourceFactory = new JdbcPooledDataSourceFactory();
        dataSourceFactory.setProperties(properties);

        CourseRepository courseRepository = new CourseRepositoryImpl(dataSourceFactory.createDataSource());

        System.out.println(courseRepository.findAll());


    }
}
