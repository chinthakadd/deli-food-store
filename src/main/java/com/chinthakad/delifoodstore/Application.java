package com.chinthakad.delifoodstore;

import com.chinthakad.delifoodstore.seedwork.util.DataLoader;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.ApplicationArguments;
import org.springframework.boot.ApplicationRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.EnableLoadTimeWeaving;
import org.springframework.context.annotation.aspectj.EnableSpringConfigured;

import static org.springframework.context.annotation.EnableLoadTimeWeaving.AspectJWeaving.ENABLED;

/**
 * TODO: Document the use {@link EnableSpringConfigured} and {@link EnableSpringConfigured}.
 */
@SpringBootApplication
@EnableSpringConfigured
@EnableLoadTimeWeaving(aspectjWeaving=ENABLED)
public class Application implements ApplicationRunner {

    @Autowired
    private DataLoader dataLoader;

    public static void main(String[] args) {
        SpringApplication.run(Application.class, args);
    }

    @Override
    public void run(ApplicationArguments args) {
        dataLoader.loadData();
    }
}
