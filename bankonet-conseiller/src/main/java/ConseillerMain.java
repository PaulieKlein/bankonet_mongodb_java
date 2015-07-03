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
		
		MongoClient mongoClient = new MongoClient("localhost");
		MongoDatabase mongoDB = mongoClient.getDatabase("bankonet");
		MongoCollection<Document> collection = mongoDB.getCollection("client");
		
		System.out.println("*****APPLICATION CONSEILLER BANCAIRE*****");
		System.out.println("0.Arrêter le programme");
		System.out.println("1.Ouvrir un compte");
		System.out.println("2.Liste des clients");
		System.out.println("Veuillez choisir une action : ");
		
		Scanner sc = new Scanner(System.in);
		
		int choice;
		choice = sc.nextInt();	
		
		switch(choice){
			
			case 1 :
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
				
				Document client = new Document()
								.append("Nom",nom)
								.append("Prenom",prenom)
								.append("Login",login)
								.append("Password",password);
								
				
				Document cc = new Document()
								.append("Libelle",libelle)
								.append("Solde",solde);
				
				List<Document> listDoc = new ArrayList<Document>();
				listDoc.add(cc);
				
				client.append("Compte Courant",listDoc);
			
				collection.insertOne(client);	
			break;
			
			case 2 :
				Iterator<Document> it = collection.find().iterator();
				List<Document> listCC;
				
		        while (it.hasNext()) {
		        	
		        	Document tmp = it.next();
		        	listCC = (List<Document>) tmp.get("Compte Courant") ;
		        	
		           System.out.println("Informations Client :\n Identifiant : "+tmp.get("_id") + 
		        		   "\nLogin : "+tmp.get("Login") +
		        		   "\nNom : "+tmp.get("Nom") +
		        		   "\nPrénom : "+tmp.get("Prenom") +
		        		   "\nNombre de comptes courants : "+ Integer.toString(listCC.size())+"\n"); 
		        }
			break;
			
			default :
				mongoClient.close();
				System.out.println("Arrêt de l'application");
		}
		if(choice==1){
			
			
			
		} else {
			
		}
		
		
	}

}
