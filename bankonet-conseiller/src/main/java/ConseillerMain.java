import java.util.Iterator;
import java.util.Scanner;
import java.util.logging.Level;
import java.util.logging.Logger;
import java.util.*;

import org.bson.Document;

import com.mongodb.MongoClient;
import com.mongodb.client.MongoCollection;
import com.mongodb.client.MongoDatabase;

public class ConseillerMain {

	public static void main(String[] args) {
		// TODO Auto-generated method stub
		Logger.getLogger("").setLevel(Level.SEVERE);
		
		MongoClient client = new MongoClient("localhost");
		MongoDatabase mongoDB = client.getDatabase("bankonet");
		
		System.out.println("*****APPLICATION CONSEILLER BANCAIRE*****");
		System.out.println("0.Arrêter le programme");
		System.out.println("1.Ouvrir un compte");
		System.out.println("Veuillez choisir une action : ");
		
		Scanner sc = new Scanner(System.in);
		
		int choice;
		choice = sc.nextInt();		
		
		if(choice==1){
			
			String nom;
			String prenom;
			String login;
			String password = "secret";
			int num;
			Double solde;
			
			System.out.println("Veuillez saisir votre nom : ");
			nom = sc.next();
			System.out.println("Veuillez saisir votre prénom : ");
			prenom = sc.next();
			System.out.println("Veuillez saisir votre login : ");
			login = sc.next();
			System.out.println("Veuillez saisir votre numéro de compte courant : ");
			num = sc.nextInt();
			System.out.println("Veuillez saisir votre solde : ");
			solde = sc.nextDouble();

			String libelle = nom+"_"+prenom+"_"+"Courant_"+num;

			
			
			MongoCollection<Document> collection = mongoDB.getCollection("client");
			Document doc = new Document()
							.append("Nom",nom)
							.append("Prenom",prenom)
							.append("Login",login)
							.append("Password",password)
							.append("Compte Courant",new Document()
														.append("Libelle",libelle)
														.append("Solde",solde));
		
			collection.insertOne(doc);
			
			/*Iterator<Document> it = collection.find().iterator();
	        while (it.hasNext()) { 
	           System.out.println(it.next()); 
	        }*/
			
			client.close();
			
		} else {
			client.close();
			System.out.println("Arrêt de l'application");
		}
		
		
	}

}
