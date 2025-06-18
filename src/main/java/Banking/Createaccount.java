package Banking;

import com.mongodb.client.MongoClient;
import com.mongodb.client.MongoClients;
import com.mongodb.client.MongoCollection;
import com.mongodb.client.MongoDatabase;
import com.mongodb.client.model.Filters;
import com.mongodb.client.model.Projections;
import com.mongodb.client.model.Updates;
import org.bson.Document;
import org.bson.conversions.Bson;

import java.util.Scanner;
import java.util.logging.Filter;

public class Createaccount {
    public void Accountcreate() {
        Scanner sc = new Scanner(System.in);
        String uri = "mongodb://localhost:27017/"; // Replace with your MongoDB URI if needed

        try (MongoClient mongoClient = MongoClients.create(uri)) {
            MongoDatabase database = mongoClient.getDatabase("Banking");
            MongoCollection<Document> booksCollection = database.getCollection("Account");

            //acc creation
            System.out.println("enter holder name");
            String name = sc.nextLine();
            System.out.println("enter Account number");
            String Accno = sc.nextLine();
            System.out.println("enter initial deposit amount");
            double Deposit = sc.nextDouble();

            Document samplebook = new Document("name", name).append("Accno", Accno).append("Deposit", Deposit);
            booksCollection.insertOne(samplebook);
        }
        catch (Exception e) {
            System.out.print(e);
        }
    }

    public void Deposit() {
        Scanner sc = new Scanner(System.in);
        String uri = "mongodb://localhost:27017/"; // Replace with your MongoDB URI if needed
        try (MongoClient mongoClient = MongoClients.create(uri)) {
            MongoDatabase database = mongoClient.getDatabase("Banking");
            MongoCollection<Document> booksCollection = database.getCollection("Account");
            Scanner Sc = new Scanner(System.in);
            System.out.println("Enter your Accno");
            String Accno = Sc.nextLine();
            System.out.println("Enter your deposit amount");
            double amount = Sc.nextDouble();

            Bson filter = Filters.eq("Accno", Accno);
            Bson Projection = Projections.fields(Projections.include("Deposit"));
            Document accountDoc = booksCollection.find(filter).projection(Projection).first();

            // Get current Deposit (balance)
            double currentDeposit = accountDoc.getDouble("Deposit");


            // Add new deposit to current deposit
            double updatedDeposit = currentDeposit + amount;
            Bson update = Updates.set("Deposit", updatedDeposit);
            booksCollection.updateOne(filter, update);
            System.out.println("Deposit successful! New balance: " + updatedDeposit);

        }
        catch (Exception e) {
            System.out.print(e);
        }
    }

    public void Widraw() {
        Scanner sc = new Scanner(System.in);
        String uri = "mongodb://localhost:27017/"; // Replace with your MongoDB URI if needed
        try (MongoClient mongoClient = MongoClients.create(uri)) {
            MongoDatabase database = mongoClient.getDatabase("Banking");
            MongoCollection<Document> booksCollection = database.getCollection("Account");
            Scanner Sc = new Scanner(System.in);
            System.out.println("Enter your Accno");
            String Accno = Sc.nextLine();
            System.out.println("Enter your Amount to Widraw");
            double amount = Sc.nextDouble();
            Bson filter = Filters.eq("Accno", Accno);
            Bson Projection = Projections.fields(Projections.include("Deposit"));
            Document accountDoc = booksCollection.find(filter).projection(Projection).first();

            // Get current Deposit (balance)
            double currentDeposit = accountDoc.getDouble("Deposit");


            // Add new deposit to current deposit
            double updatedDeposit = currentDeposit - amount;
            Bson update = Updates.set("Deposit", updatedDeposit);
            booksCollection.updateOne(filter, update);
            System.out.println("Deposit successful! New balance: " + updatedDeposit);
        }
        catch (Exception e) {
            System.out.print(e);
        }
    }

    public void balance() {
        Scanner sc = new Scanner(System.in);
        String uri = "mongodb://localhost:27017/";

        try (MongoClient mongoClient = MongoClients.create(uri)) {
            MongoDatabase database = mongoClient.getDatabase("Banking");
            MongoCollection<Document> booksCollection = database.getCollection("Account");

            System.out.println("Enter your Accno:");
            String accno = sc.nextLine(); // assuming Accno is stored as String

            // Create filter and projection
            Bson filter = Filters.eq("Accno", accno);
            Bson projection = Projections.fields(Projections.include("Deposit"));

            // Query the document
            Document accountDoc = booksCollection.find(filter).projection(projection).first();

            if (accountDoc != null) {
                double currentBalance = accountDoc.getDouble("Deposit");
                System.out.println("Current Balance: ₹" + currentBalance);
            } else {
                throw new Exception("Invalid Amount");
            }

        } catch (Exception e) {
         System.out.print(e);
        }
    }
    }
