package tech.medevs.book_sharing;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.data.jpa.repository.config.EnableJpaAuditing;

@SpringBootApplication
@EnableJpaAuditing
public class BookSharingNetworkApplication {

	public static void main(String[] args) {
		SpringApplication.run(BookSharingNetworkApplication.class, args);
	}

}
