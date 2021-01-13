package cz.zpapez.javadockerrds;

import org.bson.Document;

import com.mongodb.MongoClient;
import com.mongodb.MongoClientURI;
import com.mongodb.client.MongoCollection;
import com.mongodb.client.MongoDatabase;

public class Main {

    public static void main(String[] args) {

        String template = "mongodb://%s:%s@%s/sample-database?ssl=true&replicaSet=rs0&readpreference=%s";
        String username = "db_username";
        String password = "db_password";
        String clusterEndpoint = "my-test-cluster.cluster-abcd123.us-west-2.docdb.amazonaws.com:27017";
        String readPreference = "secondaryPreferred";
        String connectionString = String.format(template, username, password, clusterEndpoint, readPreference);

        MongoClientURI clientURI = new MongoClientURI(connectionString);
        MongoClient mongoClient = new MongoClient(clientURI);

        MongoDatabase testDB = mongoClient.getDatabase("sample-database");
        MongoCollection<Document> numbersCollection = testDB.getCollection("numbers");

        long count = numbersCollection.countDocuments();

        System.out.println("FOUND numbers " + count);

        mongoClient.close();
    }
}
