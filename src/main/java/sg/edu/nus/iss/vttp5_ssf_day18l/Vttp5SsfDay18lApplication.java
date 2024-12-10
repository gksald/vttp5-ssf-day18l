package sg.edu.nus.iss.vttp5_ssf_day18l;

import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
public class Vttp5SsfDay18lApplication {

	public static void main(String[] args) {
		SpringApplication.run(Vttp5SsfDay18lApplication.class, args);
	}

}

// // for command line runner example:
// @SpringBootApplication
// public class Vttp5SsfDay18lApplication implements CommandLineRunner {

// 	public static void main(String[] args) {
// 		SpringApplication.run(Vttp5SsfDay18lApplication.class, args);
// 	}

// 	@Override
// 	public void run(String... args) throws Exception {
// 		// read the file using FileReader

// 		// put the data into Redis Map
// 	}

// }