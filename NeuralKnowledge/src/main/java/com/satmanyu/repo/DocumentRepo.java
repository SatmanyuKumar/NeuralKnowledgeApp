package com.satmanyu.repo;

import java.util.List;
import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.satmanyu.entity.Document;

@Repository
public interface DocumentRepo extends JpaRepository<Document, Long> {

	List<Document> findAllByUserUsername(String userName);

	Optional<Document> findByIdAndUserUsername(Long id, String user);

	
	
	

}
