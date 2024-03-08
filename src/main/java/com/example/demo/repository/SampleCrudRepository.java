package com.example.demo.repository;

import org.springframework.data.repository.CrudRepository;

import com.example.demo.entity.Sample;

public interface SampleCrudRepository extends CrudRepository<Sample, Integer> {

//	@Query(value = "INSERT INTO sample VALUES(:id, :name, :gender, :age)")
//	void InsertData(@Param("id") Integer id, 
//			       @Param("name") String name, 
//			       @Param("gender") Integer gender,
//			       @Param("age") Integer age);

}