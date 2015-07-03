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
			
		Document documentClient = it.next();
		
		if (documentClient.get("Password").equals(passwordSC )) {
			connexion = true;
			System.out.println("*****APPLICATION CONSEILLER BANCAIRE*****");
    		System.out.println("0.Arrêter le programme");
    		System.out.println("1.Consulter les soldes des comptes");
    		System.out.println("2.Faire un dépôt");
    		System.out.println("Veuillez choisir une action : ");
    		int choice;
    		choice = sc.nextInt();
    		
    		switch(choice){
    		case 0:
    			mongoClient.close();
    			System.out.println("Arrêt de l'application");
    			System.exit(0);
    		break;
    		
    		case 1:
    			Iterator<Document> iter = collection.find(docnew).iterator();
    			List<Document> listCC;
		        while (iter.hasNext()) {
		        	
		        	Document tmp = iter.next();
		        	listCC = (List<Document>) tmp.get("Compte Courant") ;	
		        	Iterator<Document> compteIte =  listCC.iterator();
		    	    while (compteIte.hasNext()) {
		    	    	Document compte = (Document) compteIte.next();
		    	    	System.out.println("Informations Comptes Courants :\nLibelle : "+compte.get("Libelle") + 
				        		   "\nSolde : "+compte.get("Solde") +"\n");
		            }

		        }
    		break;
    		
    		case 2:
    			Iterator<Document> iter2 = collection.find(docnew).iterator();
    			List<Document> listCC2;
		        while (iter2.hasNext()) {
		        	
		        	Document tmp = iter2.next();
		        	listCC2 = (List<Document>) tmp.get("Compte Courant") ;	
		        	Iterator<Document> compteIte =  listCC2.iterator();
		    	    while (compteIte.hasNext()) {
		    	    	Document compte = (Document) compteIte.next();
		    	    	System.out.println("La liste de vos comptes courants :\nLibelle : "+compte.get("Libelle") + 
				        		   "\nSolde : "+compte.get("Solde") +"\n");
			    		
		            }
		    	    
		    	    
		    	    System.out.println("Veuillez choisir le compte à créditer : ");
			        String compte_depot;
			        compte_depot = sc.next();
			        System.out.println("Veuillez le montant à créditer : ");
		    		Double depot;
		    		depot = sc.nextDouble();
		    		
		    	
		    		Iterator<Document> iter3 = collection.find(docnew).iterator();
	    			List<Document> listCC3;
			        while (iter3.hasNext()) {
			        	
			        	Document tmp1 = iter3.next();
			        	listCC3 = (List<Document>) tmp1.get("Compte Courant") ;	
			        	Iterator<Document> compteIte3 =  listCC3.iterator();
			    	    while (compteIte3.hasNext()) {
			    	    	Document compte1 = (Document) compteIte3.next();
			    	    	if (compte1.get("Libelle").equals(compte_depot)) {
				    			Double solde = depot +  compte1.getDouble("Solde");					
				    			compte1.put("Solde", solde);
				 
				    			collection.updateOne(new Document("_id", tmp1.getObjectId("_id")), tmp1);
				    			
				    			System.out.println("Le compte" +compte1.get("Libelle")+ "a été crédité");
		    		
		    		}else{
		    			System.out.println("ce compte n'existe pas");
		    		}
			        }
		    		
		        }     
	    		
    		break;
		        }
    		}

        } else{
        	System.out.println("Connexion impossible");
		        }
		        
		}
		}
        
		

        
		
		
		
		

	}

}
