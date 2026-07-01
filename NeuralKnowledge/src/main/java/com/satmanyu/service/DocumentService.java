package com.satmanyu.service;

import java.io.IOException;
import java.io.InputStream;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import org.apache.tika.Tika;
import org.apache.tika.exception.TikaException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.satmanyu.entity.Document;
import com.satmanyu.entity.DocumentChunk;
import com.satmanyu.entity.MyUser;
import com.satmanyu.repo.DocumentChunkRepo;
import com.satmanyu.repo.DocumentRepo;

@Service
public class DocumentService {
	
	@Autowired
	private ObjectMapper objectMapper;
	@Autowired
	private DocumentRepo documentRepo;
	@Autowired
	private DocumentChunkRepo documentChunkRepo;
	@Autowired
	private UserService userService;
	
	
	public String save(MultipartFile file, MyUser user) throws Exception {
		String name=file.getOriginalFilename();
		try {
			InputStream is=file.getInputStream();
			System.out.println(is);
			byte doc[]=is.readAllBytes();
			System.out.println(doc.length);
			if(doc.length > 100*1000*1000) {
				return "SizeExceed";
			}else {
				
				Document d=new Document();
				d.setName(name);
				d.setDocument(doc);
				d.setUser(user);
				
				/////////////////////////////////////////
				Tika t=new Tika();
				String content=t.parseToString(file.getInputStream()).trim();
				List<String> chunks = splitText(content, 100);
				
				int chunkNumber = 1;
				Document savedDocument = documentRepo.save(d);
				for (String chunk : chunks) {

				    DocumentChunk documentChunk = new DocumentChunk();
				    
					documentChunk.setDocument(savedDocument);
				    documentChunk.setDocument(d); 
				    documentChunk.setChunkText(chunk);
				    documentChunk.setChunkNumber(chunkNumber++);

				    documentChunkRepo.save(documentChunk);
				}
				
				
				
				return "Success";
			}
		} catch (IOException | TikaException e) {
			e.printStackTrace();
			return "Failed";
		}
	}

	public List<Document> myDocument(String userName) {
		
		List<Document> chunks=documentRepo.findAllByUserUsername(userName);
		
		return chunks;
	}

	public Document getDocument(Long id) {
		
		return documentRepo.findById(id).orElse(null);
	}

	public void delete(Long id) {
//		documentChunkRepo.deleteAllById(id);
		documentRepo.deleteById(id);
		
	}
	public Optional<Document> getDocument(Long id, String user) {
		  Optional<Document> doc = documentRepo.findByIdAndUserUsername(id, user);
		  return doc;
		
	}
	public List<String> splitText(String text, int chunkSize) {

	    List<String> chunks = new ArrayList<>();
	    int start = 0;

	   while(start<text.length()) {

	        int end = Math.min(start + chunkSize, text.length());
	        if (end < text.length()) {

	            while (end > start && text.charAt(end) != ' ') {
	                end--;
	            }

	            // If no space was found, use the original limit
	            if (end == start) {
	                end = Math.min(start + chunkSize, text.length());
	            }
	        }

	        chunks.add(text.substring(start, end).trim());
	        start = end;
	    }
	    
	    return chunks;
	}
	
	
}

