package yp.db;

import java.io.FileInputStream;

import javax.annotation.PostConstruct;

import org.springframework.stereotype.Service;

import com.google.auth.oauth2.GoogleCredentials;
import com.google.firebase.FirebaseApp;
import com.google.firebase.FirebaseOptions;

@Service
public class FBInitialize {

	@PostConstruct
	public void initilize() {
		try {

			FileInputStream serviceAccount = new FileInputStream("service-key.json");

			FirebaseOptions options = new FirebaseOptions.Builder()
					.setCredentials(GoogleCredentials.fromStream(serviceAccount))
					.setDatabaseUrl("https://fir-db-for-spring-boot-5e60d.firebaseio.com").build();

			FirebaseApp.initializeApp(options);

		} catch (Exception e) {
			// TODO: handle exception
			e.printStackTrace();
		}
	}

}
