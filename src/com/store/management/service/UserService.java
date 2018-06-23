package com.store.management.service;

import org.apache.commons.codec.binary.Base64;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.RequestBody;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.store.management.domain.User;
import com.store.management.dto.Login;
import com.store.management.repository.UserRepository;

@Service
public class UserService {
	@Autowired
	private UserRepository userRepository;
	
	public ResponseEntity<String> login(@RequestBody Login login){
		User user = userRepository.findByUsername(login.getUsername());
		if(user!=null) {
			if(login.getUsername().equals(user.getUsername()) && login.getPassword().equals(user.getPassword())) {
				Gson gson = new GsonBuilder().setPrettyPrinting().create();
				String json = gson.toJson(user);
				byte[] bytesEncoded = Base64.encodeBase64(json.getBytes());
				return new ResponseEntity<>(new String(bytesEncoded), HttpStatus.OK);
			}
			else {			
				return new ResponseEntity<>("Incorrect credentials", HttpStatus.BAD_REQUEST);
			}
		}
		else {
			return new ResponseEntity<>("Not found", HttpStatus.BAD_REQUEST);
		}
	}
}
