package com.satmanyu.entity;

import java.util.ArrayList;
import java.util.List;

import jakarta.persistence.CascadeType;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.Lob;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.OneToMany;
import lombok.Data;

@Entity
@Data
public class DocumentChunk {
	
	
	
	
	  @Id
	    @GeneratedValue(strategy = GenerationType.IDENTITY)
	    private Long id;

	    private Integer chunkNumber;

	    @Lob
	    @Column(columnDefinition = "LONGTEXT")
	    private String chunkText;
	    

	    

	    
	    @ManyToOne
	    private Document document;
	    
	   
	    
	    
}
