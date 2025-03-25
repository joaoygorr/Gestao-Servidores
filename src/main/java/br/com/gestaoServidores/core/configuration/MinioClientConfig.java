package br.com.gestaoServidores.core.configuration;

import io.minio.MinioClient;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.beans.factory.annotation.Value;

@Configuration
public class MinioClientConfig {

    @Value("${spring.minio.user}")
    private String minioUser;

    @Value("${spring.minio.password}")
    private String minioPassword;

    @Bean
    MinioClient minioClient() {
        return MinioClient.builder()
                .endpoint("http://localhost:9000")
                .credentials(minioUser, minioPassword)
                .build();
    }
}
