package song.api.com.br.song;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.test.context.TestConfiguration;

@TestConfiguration(proxyBeanMethods = false)
public class TestSongApplication {

	public static void main(String[] args) {
		SpringApplication.from(SongApplication::main).with(TestSongApplication.class).run(args);
	}

}
