package com.demo.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.demo.domain.user.User;
import com.demo.dtos.UserDTO;
import com.demo.services.UserService;

@RestController
@RequestMapping("/users")
public class UserController {
	
		@Autowired
		private UserService userService;

		@PostMapping
		public ResponseEntity<User> createUser(@RequestBody UserDTO user){
			User newUser = userService.criarUser(user);
			return new ResponseEntity<>(newUser,HttpStatus.CREATED);
			
		}
		
		@GetMapping
		public ResponseEntity<List<User>> gettAllUsers(){
			List<User> users = this.userService.getAllUsers();
			return new ResponseEntity<>(users,HttpStatus.OK);
		}
}
