package org.example;
import com.mongodb.client.MongoClient;
import com.mongodb.client.MongoClients;
import com.mongodb.client.MongoCollection;
import com.mongodb.client.MongoDatabase;
import com.mongodb.client.model.Filters;
import com.mongodb.client.model.Updates;
import com.mongodb.client.result.UpdateResult;
import org.bson.Document;
import org.bson.conversions.Bson;

import java.util.Collections;
import java.util.Scanner;

public class book {
    public void insertbook() {
        Scanner sc = new Scanner(System.in);
        String uri = "mongodb://localhost:27017/"; // Replace with your MongoDB URI if needed

        try (MongoClient mongoClient = MongoClients.create(uri)) {
            MongoDatabase database = mongoClient.getDatabase("LIBRARY");
            MongoCollection<Document> booksCollection = database.getCollection("BOOKS");

            //
            System.out.println("enter the book-Title");
            String title = sc.nextLine();
            System.out.println("enter the Author");
            String author = sc.nextLine();

            //category

            System.out.println("select category of the book");
            System.out.println("1.Fiction");
            System.out.println("2.Non Fiction");
            int ch = sc.nextInt();
            //
            String ct = "";
            if (ch == 1)
                ct = "Fiction";
            else if (ch == 2)
                ct = "Non Fiction";
            else
                System.out.print("");
            Document samplebook = new Document("author", author).append("Title", title).append("category", ct);
            booksCollection.insertOne(samplebook);
        }
    }

    public void updatebook() {
        Scanner sc = new Scanner(System.in);
        String uri = "mongodb://localhost:27017/"; // Replace with your MongoDB URI if needed

        try (MongoClient mongoClient = MongoClients.create(uri)) {
            MongoDatabase database = mongoClient.getDatabase("LIBRARY");
            MongoCollection<Document> booksCollection = database.getCollection("BOOKS");


            System.out.println("enter your choice");
            System.out.println("1.author");
            System.out.println("2.Title");
            System.out.println("3.category");
            int ch = sc.nextInt();
            sc.nextLine();
            switch (ch) {
                case 1:
                    System.out.println("enter your Title of the Book");
                    String title = sc.nextLine();
                    System.out.println("enter your new Title name of the Book");
                    String ntitle = sc.nextLine();
                    Bson filter = Filters.eq("Title", title);
                    Bson update = Updates.set("author", ntitle);
// Updates first matching document
                    UpdateResult result = booksCollection.updateOne(filter, update);
                    break;
                case 2:
                    System.out.println("enter your Title name");
                    String title2 = sc.nextLine();
                    System.out.println("enter your new Title name");
                    String mtitle2 = sc.nextLine();
                    Bson filter2 = Filters.eq("Title", title2);
                    Bson update2 = Updates.set("Title", mtitle2);
// Updates first matching document
                    UpdateResult result2 = booksCollection.updateOne(filter2, update2);
                    break;
                case 3:
                    System.out.println("enter your Title name");
                    String title3 = sc.nextLine();
                    System.out.println("enter your new Category name");
                    String mtitle3 = sc.nextLine();
                    Bson filter3 = Filters.eq("Title", title3);
                    Bson update3 = Updates.set("category", mtitle3);
// Updates first matching document
                    UpdateResult result3 = booksCollection.updateOne(filter3, update3);
                    break;
            }
        } catch (Exception e) {
            System.out.print(e);
        }
    }

    public void displaybook() {
        Scanner sc = new Scanner(System.in);
        String uri = "mongodb://localhost:27017/"; // Replace with your MongoDB URI if needed

        try (MongoClient mongoClient = MongoClients.create(uri)) {
            MongoDatabase database = mongoClient.getDatabase("LIBRARY");
            MongoCollection<Document> booksCollection = database.getCollection("BOOKS");


            System.out.println("enter your choice to display");
            System.out.println("1.author");
            System.out.println("2.Title");
            System.out.println("3.category");
            int ch = sc.nextInt();
            sc.nextLine();
            switch (ch) {
                case 1:
                    System.out.println("enter your Title of the Book");
                    String title = sc.nextLine();
                    Bson filter = Filters.eq("Title", title);
                    booksCollection.find(filter).forEach(doc -> System.out.println(doc.toJson()));
                    break;
                case 2:
                    System.out.println("enter your Author of the Book");
                    String mtitle = sc.nextLine();
                    Bson mfilter = Filters.eq("author", mtitle);
                    booksCollection.find(mfilter).forEach(doc -> System.out.println(doc.toJson()));
                    break;
                case 3:
                    System.out.println("enter your category of the Book");
                    String ntitle = sc.nextLine();
                    Bson nfilter = Filters.eq("category", ntitle);
                    booksCollection.find(nfilter).forEach(doc -> System.out.println(doc.toJson()));
                    break;
            }
        } catch (Exception e) {
            System.out.print(e);
        }
    }

    public void deletebook() {
        Scanner sc = new Scanner(System.in);
        String uri = "mongodb://localhost:27017/"; // Replace with your MongoDB URI if needed

        try (MongoClient mongoClient = MongoClients.create(uri)) {
            MongoDatabase database = mongoClient.getDatabase("LIBRARY");
            MongoCollection<Document> booksCollection = database.getCollection("BOOKS");
            System.out.println("Enter the Title to Delete");
            String ch = sc.nextLine();
            Bson filter = Filters.eq("Title", ch);
            booksCollection.deleteMany(filter);
        } catch (Exception e) {
            System.out.print(e);
        }
    }
}