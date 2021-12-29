package com.kyungmin.exampleRest.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.util.UriComponentsBuilder;

import com.kyungmin.exampleRest.exception.UserDuplicatedException;
import com.kyungmin.exampleRest.exception.UserNotFoundException;
import com.kyungmin.exampleRest.model.User;
import com.kyungmin.exampleRest.service.UserService;

@RestController // @Controller + @ResponseBody
@RequestMapping("/api")
public class RestApiController {

	@Autowired
	UserService userService;
	
	//전체 User 조회(Read)
	@GetMapping(value = "/users") //@RequestMapping(value="/users", method=RequestMethod.GET)
	public ResponseEntity<List<User>> listAllUsers() {
		List<User> users = userService.findAllUsers();
		if(users.isEmpty())
			return new ResponseEntity<>(HttpStatus.NO_CONTENT); // status
		
		return new ResponseEntity<List<User>>(users, HttpStatus.OK); // body(JSON), status
	}
	
	//ID 기반 User 조회(Read)
	@GetMapping(value = "/users/{id}") ////@RequestMapping(value="/users{id}", method=RequestMethod.GET)
	public ResponseEntity<User> getUser(@PathVariable("id") long id) {
		User user = userService.findById(id);
		if(user == null) {
			throw new UserNotFoundException(id);
		}
		return new ResponseEntity<User>(user, HttpStatus.OK);
	}
	
	//새로운 User 생성(Create)
	@PostMapping(value = "/users") //@RequestMapping(value="/users", method=RequestMethod.POST)
	public ResponseEntity<Void> createUser(@RequestBody User user, UriComponentsBuilder ucBuilder) {
		if(userService.doesUserExist(user)) { //user 존재여부 확인
			throw new UserDuplicatedException(user.getName());
		}
		
		userService.saveUser(user);
		
		// 사용자가 접근할 URI를 생성
		HttpHeaders headers = new HttpHeaders();
		headers.setLocation(ucBuilder.path("/api/user/{id}").buildAndExpand(user.getId()).toUri());
		return new ResponseEntity<>(headers, HttpStatus.CREATED);
	}
	
	//ID 기반 User 갱신(Update)
	@PutMapping(value = "/users/{id}") //@RequestMapping(value="/users/{id}", method=RequestMethod.PUT)
	public ResponseEntity<User> updateUser(@PathVariable("id") long id, @RequestBody User user) {
		User currentUser = userService.findById(id);
		
		if(currentUser == null) {
			throw new UserNotFoundException(id);
		}
		
		currentUser.setName(user.getName());
		currentUser.setAge(user.getAge());
		currentUser.setSalary(user.getSalary());
		
		userService.updateUser(currentUser);
		
		return new ResponseEntity<User>(currentUser, HttpStatus.OK);
	}
	
	//ID 기반 User 삭제(Delete)
	@DeleteMapping(value = "/users/{id}") //@RequestMapping(value="/users/{id}", method=RequestMethod.DELETE)
	public ResponseEntity<Void> deleteUser(@PathVariable("id") long id) {
		User user = userService.findById(id);
		if(user == null) {
			throw new UserNotFoundException(id);
		}
		userService.deleteUserById(id);
		return new ResponseEntity<>(HttpStatus.NO_CONTENT);
	}
	
	//전체 User 삭제(Delete)
	@DeleteMapping(value = "/users")
	public ResponseEntity<Void> deleteAllUsers() {
		userService.deleteAllUsers();
		return new ResponseEntity<>(HttpStatus.NO_CONTENT);
	}
}
