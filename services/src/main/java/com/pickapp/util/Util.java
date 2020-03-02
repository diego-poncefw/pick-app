package com.pickapp.util;

import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.math.BigDecimal;
import java.util.Random;

import org.springframework.util.ResourceUtils;

import com.google.auth.oauth2.GoogleCredentials;
import com.google.firebase.FirebaseApp;
import com.google.firebase.FirebaseOptions;
import com.pickapp.services.service.MachineService;
import com.pickapp.services.service.UserService;

public class Util {

	public static BigDecimal genUserCode(UserService userService) {
		boolean unique = false;
		int rand = 0;
		do {
			Random r = new Random(System.currentTimeMillis());
			rand = ((1 + r.nextInt(2)) * 10000 + r.nextInt(10000));
			unique = userService.findByCode(new BigDecimal(rand)).isEmpty();
		} while (!unique);
		return new BigDecimal(rand);
	}

	public static Integer genMachineCode(MachineService machineService) {
		boolean unique = false;
		int rand = 0;
		do {
			Random r = new Random(System.currentTimeMillis());
			rand = ((1 + r.nextInt(2)) * 10000 + r.nextInt(10000));
			unique = machineService.findByNumber(rand).isEmpty();
		} while (!unique);
		return rand;
	}

	public static void initFirebase() throws IOException {

		File file = ResourceUtils.getFile("classpath:pick-app-d089d-firebase-adminsdk-4pfyo-0da32e9bca.json");
		FileInputStream serviceAccount = new FileInputStream(file);

		FirebaseOptions options = new FirebaseOptions.Builder()
				.setCredentials(GoogleCredentials.fromStream(serviceAccount))
				.setDatabaseUrl("https://pick-app-d089d.firebaseio.com").build();

		FirebaseApp fapp =FirebaseApp.initializeApp(options);
		System.out.println("app name: "+fapp.getName());
	}

}
