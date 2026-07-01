//package com.satmanyu.service;
//
//import java.util.HashMap;
//import java.util.List;
//import java.util.Map;
//
//import org.springframework.beans.factory.annotation.Autowired;
//import org.springframework.beans.factory.annotation.Value;
//import org.springframework.scheduling.annotation.Async;
//import org.springframework.stereotype.Service;
//import org.springframework.web.client.RestClient;
//
//import com.fasterxml.jackson.core.type.TypeReference;
//import com.fasterxml.jackson.databind.ObjectMapper;
//import com.satmanyu.entity.DocumentChunk;
//import com.satmanyu.repo.DocumentChunkRepo;
//
//@Service
//public class EmbeddingService {
//	@Autowired
//	private ObjectMapper objectMapper;
//	@Autowired
//	private DocumentChunkRepo documentChunkRepo;
//
//    @Value("${huggingface.api.key}")
//    private String apiKey;
//
//    private final RestClient restClient;
//
//    public EmbeddingService(RestClient.Builder builder) {
//        this.restClient = builder.build();
//    }
//    
////    private static final String URL =
////            "https://api-inference.huggingface.co/models/sentence-transformers/all-MiniLM-L6-v2";
//    private static final String URL =
//    	    "https://router.huggingface.co/hf-inference/models/sentence-transformers/all-MiniLM-L6-v2/pipeline/feature-extraction";
//    public List<Double> getEmbedding(String text) throws Exception {
//
//        Map<String, String> request = new HashMap<>();
//        request.put("inputs", text);
//
//        String response = restClient.post()
//                .uri(URL)
//                .header("Authorization", "Bearer " + apiKey)
//                .header("Content-Type", "application/json")
//                .body(request)
//                .retrieve()
//                .body(String.class);
//
//        return objectMapper.readValue(
//                response,
//                new TypeReference<List<Double>>() {});
//    }
//    @Async
//    public void generateEmbeddings(Long documentId) throws Exception {
//
//        List<DocumentChunk> chunks =
//                documentChunkRepo.findAllByDocumentId(documentId);
//
//        for (DocumentChunk chunk : chunks) {
//
//            List<Double> vector = getEmbedding(chunk.getChunkText());
//
//            chunk.setEmbedding(
//                objectMapper.writeValueAsString(vector));
//
//            documentChunkRepo.save(chunk);
//        }
//    }
//    
//}
