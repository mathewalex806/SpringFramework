package com.alex.example;

import dev.langchain4j.data.embedding.Embedding;
import dev.langchain4j.model.output.Response;
import dev.langchain4j.model.vertexai.VertexAiEmbeddingModel;
import org.springframework.stereotype.Service;

@Service
public class EmbeddingModel
{
    private static final String PROJECT_ID = "request-matching";
    private static final String MODEL_NAME = "text-embedding-004";

    public float[] createEmbedding()
    {
        VertexAiEmbeddingModel embeddingModel = VertexAiEmbeddingModel.builder()
                .project(PROJECT_ID)
                .location("us-central1")
                .endpoint("us-central1-aiplatform.googleapis.com:443")
                .publisher("google")
                .modelName(MODEL_NAME)
                .build();

        Response<Embedding> response = embeddingModel.embed("Hello, how are you?");

        Embedding embedding = response.content();

        int dimension = embedding.dimension(); // 768
        float[] vector = embedding.vector(); // [-0.06050122, -0.046411075, ...

        System.out.println(dimension);
        System.out.println(embedding.vectorAsList());
        return vector;

    }

}
