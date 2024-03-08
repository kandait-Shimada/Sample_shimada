package com.example.demo.service;

import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.example.demo.entity.Sample;
import com.example.demo.repository.SampleCrudRepository;


@Service
@Transactional
public class SampleServiceImpl implements SampleService {

	@Autowired
	SampleCrudRepository repository;
	
	
	@Override
	public Iterable<Sample> SelectAll() {
		
		return repository.findAll();
	}

	@Override
	public Optional<Sample> SlectOneById(Integer id) {
		return repository.findById(id);
	}

	@Override
	public void InsertSample(Sample sample) {
		
		 repository.save(sample);
	}

	@Override
	public void UpdateSample(Sample sample) {
		repository.save(sample);

	}

	@Override
	public void DeleteSampleById(Integer id) {
		repository.deleteById(id);
		
	}
}
