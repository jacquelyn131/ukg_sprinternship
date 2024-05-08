package com.example.ukgtime;

import java.io.FileInputStream;
import java.io.IOException;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

import com.google.auth.oauth2.GoogleCredentials;
import com.google.firebase.FirebaseApp;
import com.google.firebase.FirebaseOptions;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseAuthException;

@SpringBootApplication
public class UkgTimeApplication {

	public static void main(String[] args) throws IOException, FirebaseAuthException {
		SpringApplication.run(UkgTimeApplication.class, args);
		
		// FirebaseOptions options = FirebaseOptions.builder()
		// .setCredentials(GoogleCredentials.getApplicationDefault())
		// .setDatabaseUrl("https://<DATABASE_NAME>.firebaseio.com/")
		// .build();

		FileInputStream serviceAccount = new FileInputStream("keys\\firebaseKeys.json");

		@SuppressWarnings("deprecation")
		FirebaseOptions options = new FirebaseOptions.Builder()
		.setCredentials(GoogleCredentials.fromStream(serviceAccount))
		.build();

		FirebaseApp app = FirebaseApp.initializeApp(options);
		FirebaseAuth defaultAuth = FirebaseAuth.getInstance(app);
	}

}
