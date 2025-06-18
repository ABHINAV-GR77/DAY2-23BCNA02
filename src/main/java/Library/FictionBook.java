package Library;

import com.mongodb.client.MongoClient;
import com.mongodb.client.MongoClients;
import com.mongodb.client.MongoCollection;
import com.mongodb.client.MongoDatabase;
import com.mongodb.client.model.Filters;
import com.mongodb.client.model.Updates;
import com.mongodb.client.result.UpdateResult;
import org.bson.Document;
import org.bson.conversions.Bson;

import java.util.Scanner;

public class FictionBook {
    public void findbook() {
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
}
