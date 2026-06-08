package co.edu.udec.tienda_musica;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication(scanBasePackages = "co.edu.udec.tienda_musica")
public class TiendaMusicaApplication {

    public static void main(String[] args) {
        SpringApplication.run(TiendaMusicaApplication.class, args);
    }
}
