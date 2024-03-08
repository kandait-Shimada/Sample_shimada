package com.example.demo.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.example.demo.entity.Login;
import com.example.demo.repository.LoginRepository;

@Service
public class LoginService {

	@Autowired
	private LoginRepository repository;

	public boolean authenticate(String username, String password) {
		Login login = repository.findByUsernameAndPassword(username, password);
		return login != null;
	}
	
	public boolean registerUser(String username, String password) {
        // ユーザー名の存在チェック
        Login existingUser = repository.findByUsername(username);
        if (existingUser != null) {
            
            return false;
        }
        
        // 新しいユーザーを作成して保存します。
        Login newUser = new Login();
        newUser.setUsername(username);
        newUser.setPassword(password);
        repository.save(newUser);
        return true;
    }
}
