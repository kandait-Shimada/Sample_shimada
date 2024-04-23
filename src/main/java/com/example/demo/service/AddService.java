package com.example.demo.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.example.demo.entity.TelInfo;
import com.example.demo.entity.UserInfo;
import com.example.demo.repository.TelInfoRepository;
import com.example.demo.repository.UserInfoRepository;

@Service
public class AddService {

	@Autowired
	private UserInfoRepository userInfoRepository;

	@Autowired
	private TelInfoRepository telInfoRepository;

	public UserInfo add1(UserInfo userInfo) {
		// このコメントは後で消して
		// コントローラークラスで自動生成したIDの受け渡しを行う
		return userInfoRepository.save(userInfo);
	}

	public void add2(List<TelInfo> telInfos) {

		for (TelInfo telInfo : telInfos) {
			telInfoRepository.save(telInfo);
		}
	}

}
