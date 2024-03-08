package com.example.demo.service;

import java.util.Optional;

import com.example.demo.entity.Sample;

public interface SampleService {

	/*全権取得*/
	Iterable<Sample> SelectAll();
	
	/*
	 * idをキーにして1県取得
	 * 戻り値はOptional型
	 * isPresent()を使用して値があるときはtrueになる
	 */
	Optional<Sample> SlectOneById(Integer id);
	
	/* 取得したデータをDBにInsertする */
	void InsertSample(Sample sample);
	
	/* データを更新する */
	void UpdateSample(Sample sample);
	
	/* データを削除する */
    void DeleteSampleById(Integer id);
	

}
