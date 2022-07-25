package com.signusstudio.survey.app.survey.controllers;


import com.mongodb.MongoException;
import com.mongodb.client.*;
import static com.mongodb.client.model.Filters.*;
import com.mongodb.client.result.InsertOneResult;
import org.bson.Document;
import org.bson.conversions.Bson;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import javax.print.Doc;
import java.io.IOException;
import java.util.*;

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

    @GetMapping("/{code}/user/{userid}")
    public List<Document> getSurveryByCodeAndUser( @PathVariable(value = "code") String code, @PathVariable(value = "user") String userid ) {
        List<Document> docs = new ArrayList<>();
        FindIterable<Document> iterDoc = null;
        MongoDatabase database = this.mongoClient.getDatabase("surveys");
        MongoCollection<Document> collection = database.getCollection("paes");
        try {
            Bson comparation = and( eq("code", code), eq("createdBy", userid));
            iterDoc = collection.find(comparation);
            Iterator it = iterDoc.iterator();
            while (it.hasNext()){
                docs.add((Document) it.next());
            }
        } catch (MongoException me) {
            System.err.println("Unable to insert due to an error: " + me);
        }
        return docs;
    }
    @GetMapping("/{code}")
    public List<Document> getSurverys(@PathVariable(value = "surveyCode") String surveyCode) {
        List<Document> docs = new ArrayList<>();
        FindIterable<Document> iterDoc = null;
        MongoDatabase database = this.mongoClient.getDatabase("surveys");
        MongoCollection<Document> collection = database.getCollection("paes");
        try {
            iterDoc = collection.find(eq("surveyCode", surveyCode));
            Iterator it = iterDoc.iterator();
            while (it.hasNext()){
                docs.add((Document) it.next());
            }
        } catch (MongoException me) {
            System.err.println("Unable to insert due to an error: " + me);
        }
        return docs;
    }

    @GetMapping("/role/{role}")
    public Document getSurveryByRole(@PathVariable(value ="role") String role ) {
        Document doc = null;
        MongoDatabase database = this.mongoClient.getDatabase("surveys");
        MongoCollection<Document> collection = database.getCollection("paes");
        try {
            doc = collection.find(eq("id", role)).first();
            if (doc != null) {
                return doc;
            }
        } catch (MongoException me) {
            System.err.println("Unable to insert due to an error: " + me);
        }
        return doc;
    }

    }
