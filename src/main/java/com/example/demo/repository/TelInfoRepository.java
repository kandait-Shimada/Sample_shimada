//検索機能実装時のエンティティクラス修正のため使用しない
//package com.example.demo.repository;
//
//import org.springframework.data.jdbc.repository.query.Modifying;
//import org.springframework.data.jdbc.repository.query.Query;
//import org.springframework.data.repository.CrudRepository;
//import org.springframework.data.repository.query.Param;
//import org.springframework.stereotype.Repository;
//import org.springframework.transaction.annotation.Transactional;
//
//import com.example.demo.entity.TelInfo;
//
//@Repository
//public interface TelInfoRepository extends CrudRepository<TelInfo, String> {
//	@Modifying
//	@Transactional
//	@Query("INSERT INTO TelInfo (tel, customer_ID, telorder) VALUES (:TEL, :CUSTOMER_ID, :TELORDER)")
//	void insertTelInfo(@Param("TEL") String TEL, @Param("CUSTOMER_ID") Integer CUSTOMER_ID,
//			@Param("TELORDER") Integer TELORDER);
//}
//	
//
