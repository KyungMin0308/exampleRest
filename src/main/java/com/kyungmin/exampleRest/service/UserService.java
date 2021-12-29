package com.kyungmin.exampleRest.service;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
import java.util.concurrent.atomic.AtomicLong;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.kyungmin.exampleRest.model.User;

@Service
@Transactional
public class UserService {

	//AtomicLong이란? 상호배제를 적용시킨 클래스, Tomcat은 multi-thread이기 때문에
	private static final AtomicLong counter = new AtomicLong();
	
	//User를 저장할 리스트, DB를 사용하지 않고 메모리상에 저장하기 위함(DAO 클래스 사용X)
	private static List<User> users;
	
	public UserService() {
		users = new ArrayList<User>();
		//incrementAndGet: 증가시키고 가져옴
		users.add(new User(counter.incrementAndGet(), "Alice", 30, 70000));
		users.add(new User(counter.incrementAndGet(), "Bob", 40, 50000));
		users.add(new User(counter.incrementAndGet(), "Charlie", 45, 30000));
		users.add(new User(counter.incrementAndGet(), "Daniel", 50, 40000));
	}
	
	//전체 user 조회(Read)
	public List<User> findAllUsers() {
		return users;
	}
	
	//ID 기반 user 조회(Read)
	public User findById(long id) {
		for(User user: users) {
			if(user.getId() == id)
				return user;
		}
		return null;
	}
	
	//Name 기반 user 조회(Read)
	public User findByName(String name) {
		for(User user: users) {
			if(user.getName().equalsIgnoreCase(name))
				return user;
		}
		return null;
	}
	
	//user 저장(Create)
	public void saveUser(User user) {
		user.setId(counter.incrementAndGet());
		users.add(user);
	}
	
	//user 갱신(Update)
	public void updateUser(User user) {
		int index = users.indexOf(user);
		users.set(index, user);
	}
	
	//ID 기반 user 삭제(Delete)
	public void deleteUserById(long id) {
		for(Iterator<User> iterator = users.iterator(); iterator.hasNext();) {
			User user = iterator.next();
			if(user.getId() == id)
				iterator.remove();
		}
	}
	
	//user가 존재하는지 확인
	public boolean doesUserExist(User user) {
		return findByName(user.getName()) != null;
	}
	
	//전체 user 삭제(Delete)
	public void deleteAllUsers() {
		users.clear();
	}
}

