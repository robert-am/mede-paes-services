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
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

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
        MongoDatabase database = this.mongoClient.getDatabase("PAES0");
        MongoCollection<Document> collection = database.getCollection("surveys");
        try {
            result = collection.insertOne(document);
        } catch (MongoException me) {
            System.err.println("Unable to insert due to an error: " + me);
        }
        return result.toString();
    }

    @GetMapping("/")
    public Document getSurvery() {
        Document doc = null;
        MongoDatabase database = this.mongoClient.getDatabase("PAES0");
        MongoCollection<Document> collection = database.getCollection("surveys");
        try {
            doc = collection.find(Filters.eq("questions.dato1", "example")).first();
            if (doc != null) {
                return doc;
            }
        } catch (MongoException me) {
            System.err.println("Unable to insert due to an error: " + me);
        }
        return doc;
    }
}
