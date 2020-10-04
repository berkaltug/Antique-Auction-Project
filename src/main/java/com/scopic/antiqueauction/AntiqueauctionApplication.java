package com.scopic.antiqueauction;

import com.scopic.antiqueauction.utils.FileStorageProperties;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.context.properties.EnableConfigurationProperties;

@SpringBootApplication
@EnableConfigurationProperties({
        FileStorageProperties.class
})
public class AntiqueauctionApplication {

    public static void main(String[] args) {
        SpringApplication.run(AntiqueauctionApplication.class, args);
    }

}
