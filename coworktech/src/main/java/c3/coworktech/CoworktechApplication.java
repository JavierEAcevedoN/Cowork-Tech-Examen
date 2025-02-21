package c3.coworktech;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

import c3.coworktech.service.EspaciosService;

@SpringBootApplication
public class CoworktechApplication implements CommandLineRunner{

	public static void main(String[] args) {
		SpringApplication.run(CoworktechApplication.class, args);
	}

	@Autowired
	private EspaciosService espaciosService;

	@Override
	public void run(String... args) throws Exception {
		System.out.println("-".repeat(100));
		espaciosService.getEspacios().forEach(i->System.out.println(i));
		System.out.println("-".repeat(100));
	}
}