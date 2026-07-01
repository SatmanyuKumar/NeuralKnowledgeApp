package com.satmanyu.repo;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.satmanyu.entity.DocumentChunk;


@Repository
public interface DocumentChunkRepo extends JpaRepository<DocumentChunk, Long> {

	List<DocumentChunk> findAllByDocumentId(Long documentId);

	List<DocumentChunk> findAllByDocumentIdOrderByChunkNumber(Long documentId);

	void deleteAllById(Long id);

	
	
	

}

