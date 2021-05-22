package com.edu.poli.proyecto;

import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;

public class PassEncrypt {

	public static void main(String[] args) {

		String password = "1234";
		BCryptPasswordEncoder passwordEncoder = new BCryptPasswordEncoder();
		String hashedPassword = passwordEncoder.encode(password);

		System.out.println(hashedPassword);

	}
}
