package com.signusstudio.survey.app.survey.controllers;


import com.mongodb.MongoException;
import com.mongodb.client.MongoClient;
import com.mongodb.client.MongoClients;
import com.mongodb.client.MongoCollection;
import com.mongodb.client.MongoDatabase;
import com.mongodb.client.model.Filters;
import com.mongodb.client.result.InsertOneResult;
import org.bson.Document;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import javax.print.Doc;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Properties;

@RestController
public class SurveyController {

    private final Logger log = LoggerFactory.getLogger(SurveyController.class);

    @Autowired
    MongoClient mongoClient;

    @PostMapping("/")
    public String saveSurvey(@RequestBody Document document) throws IOException {
        InsertOneResult result = null;
        MongoDatabase database = this.mongoClient.getDatabase("surveys");
        MongoCollection<Document> collection = database.getCollection("paes");
        try {
            result = collection.insertOne(document);
        } catch (MongoException me) {
            System.err.println("Unable to insert due to an error: " + me);
        }
        return result.toString();
    }

    @GetMapping("/name/{name}")
    public Document getSurveryByName( @PathVariable(value = "name") String name ) {
        Document docs = null;
        MongoDatabase database = this.mongoClient.getDatabase("surveys");
        MongoCollection<Document> collection = database.getCollection("paes");
        try {
            docs = collection.find( Filters.eq("name", name)).first();
            if (docs != null) {
                return docs;
            }
        } catch (MongoException me) {
            System.err.println("Unable to insert due to an error: " + me);
        }
        return docs;
    }
    @GetMapping("/{id}")
    public Document getSurveryById(@PathVariable(value ="id") String id ) {
        Document doc = null;
        MongoDatabase database = this.mongoClient.getDatabase("surveys");
        MongoCollection<Document> collection = database.getCollection("paes");
        try {
            doc = collection.find(Filters.eq("id", id)).first();
            if (doc != null) {
                return doc;
            }
        } catch (MongoException me) {
            System.err.println("Unable to insert due to an error: " + me);
        }
        return doc;
    }
}
