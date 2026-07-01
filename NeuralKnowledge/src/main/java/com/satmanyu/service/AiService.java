package com.satmanyu.service;

import java.util.List;

import org.springframework.ai.chat.client.ChatClient;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.satmanyu.entity.DocumentChunk;
import com.satmanyu.repo.DocumentChunkRepo;

@Service
public class AiService {
	
    private final ChatClient chatClient;
    
    @Autowired
    private DocumentChunkRepo documentChunkRepo;
    
 
  
    public AiService(ChatClient.Builder builder) {
        this.chatClient = builder.build();
    }

    /**
     * General AI question (no document context)
     */
  
    
    

    /**
     * Document-specific question answering (RAG-lite)
     */
    
    
    public String askFromDocument(String question, String content) {

        if (content == null || content.isBlank()) {
            return "The selected document has no readable content.";
        }

        String prompt = """
                You are an AI assistant.
                Answer strictly based on the document content provided below.
                If the answer is not present in the document, reply exactly:
                "The document does not contain this information."

                Document Content:
                ----------------
                %s

                Question:
                --------
                %s
                """.formatted(content, question);


    return chatClient.prompt()
            .system("You answer only from the given document.")
            .user(prompt)
            .call()
            .content();


    }

//    public String ask(String question, Long documentId) {
//
//       
//
//        StringBuilder context = new StringBuilder();
//      
//
//        String prompt = """
//                Context:
//                %s
//
//                Question:
//                %s
//
//                Answer only from the given context.
//                If the answer is not present, say:
//                "The answer is not available in the document."
//                """.formatted(context, question);
//
//        return chatClient.prompt()
//                .system("You are a helpful AI assistant. Answer only from the provided context.")
//                .user(prompt)
//                .call()
//                .content();
//    }

}
