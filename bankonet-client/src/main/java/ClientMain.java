import java.util.Iterator;
import java.util.List;
import java.util.Scanner;
import java.util.logging.Level;
import java.util.logging.Logger;

import org.bson.Document;

import com.mongodb.MongoClient;
import com.mongodb.client.MongoCollection;
import com.mongodb.client.MongoDatabase;


public class ClientMain {

	public static void main(String[] args) {
		// TODO Auto-generated method stub
		
Logger.getLogger("").setLevel(Level.SEVERE);
		
		MongoClient mongoClient = new MongoClient("localhost");
		MongoDatabase mongoDB = mongoClient.getDatabase("bankonet");
		MongoCollection<Document> collection = mongoDB.getCollection("client");
		
		Boolean connexion =false;
		
		
		
		Scanner sc = new Scanner(System.in);
		while(!connexion){
		String loginSC;
		String passwordSC;
		
		System.out.println("Veuillez saisir un login : ");
		loginSC = sc.next();	
		System.out.println("Veuillez saisir un mot de passe : ");
		passwordSC = sc.next();
		
		Document docnew = new Document().append("Login", loginSC);
		
		Iterator<Document> it = collection.find(docnew).iterator();
		
		
		while(it.hasNext() ){
			
		if (it.next().get("Password").equals(passwordSC )) {
			connexion = true;
			System.out.println("*****APPLICATION CONSEILLER BANCAIRE*****");
    		System.out.println("0.Arrêter le programme");
    		System.out.println("1.Consulter les soldes des comptes");
    		System.out.println("Veuillez choisir une action : ");
    		int choice;
    		choice = sc.nextInt();
    		
    		switch(choice){
    		case 0:
    			mongoClient.close();
    			System.out.println("Arrêt de l'application");
    			System.exit(0);
    		break;
		
    		}

        } else{
        	System.out.println("Connexion impossible");
		        }
		        
		}
		}
        
		

        
		
		
		
		

	}

}
