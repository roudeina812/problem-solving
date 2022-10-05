package probsolv;

import java.util.ArrayList;
import java.util.Scanner;

public class mainGame {

	public static void main(String[] args) {
		boolean fin=false;
		ArrayList l=new ArrayList<noeud>();
		ArrayList l2=new ArrayList<noeud>();
		Scanner s=new Scanner(System.in);

		l2=MontagneBrunchAndBound.remplirArbre();
		MontagneBrunchAndBound.genererGraph();
		MontagneBrunchAndBound.genererCouleur();
		MontagneBrunchAndBound.resoudremontagne();
		System.out.println("\n");
		System.out.println("********************La deuxiéme partie du jeu va commencer maintenant !********************");
		System.out.println("\n");
		System.out.println("  Dans cette deuxiéme partie, vous devez gramper une montagne d’un\n"
		+ "  point de depart a un point d’arriver de telle sorte que vous minimisez la distance\n"
		+ "  parcourue.");
		System.out.println("  Un ensemble de piquets sont fixes sur cette montagne.\n"
		+ "  Vous devez se deplacer d’un piquet a un autre et donc d'un niveau a un autre .\n"
		+ "  Il existe 4 niveaux .\n"
		+ "  il faut passer par un piquet dans chaque niveau .\n"
		+ "  Les piquets sont de couleurs differentes : Rouge, Bleu, Vert, Jaune, Noir, Argent, Marron, Gris, et DORÉ"
		+ "  Tapez * si vous voulez Quitter le jeu.\n"
		+ "  le piquet de premier niveau et le rouge et dernier le doré .\n");
		System.out.println("\n");
		System.out.println("        *********************        ");
		System.out.println("        Voici le plan du jeu :");
		System.out.println("        *********************        ");
		System.out.println("\n");
		System.out.println("                           D                       ");
		System.out.println("\n");
		System.out.println("                               M             G     ");
		System.out.println("\n");
		System.out.println("              N                         A          ");
		System.out.println("\n");
		System.out.println("         B               V                J        ");
		System.out.println("\n");
		System.out.println("                           R                       ");
		System.out.println("\n");
		System.out.println("        *********************        ");
		System.out.println("\n");

		noeud n=new noeud('R',0,1);
		l.add(n);
		String str;
		char c;
		char c_maj;
		
		boolean testQuitter=false;
		boolean test=false;
		int x=0;
		
		long end= System.currentTimeMillis()+60000;
        long debut=System.currentTimeMillis();
        
        		debut=System.currentTimeMillis();
				while (test==false && testQuitter==false && debut<end) {
					System.out.println("\nVous avez "+((end-debut)/1000)+" secondes pour terminer ce stage du jeu");
					System.out.println("Vous etes placés maintenant au niveau du piquet Rouge: R ");
					System.out.println("Au premier niveau, Vous pouvez choisir les couleurs des piquets suivant : Bleu : B, Vert: V, Jaune: J :");
					System.out.println("Vous devez écrire la premiére lettre de la coulaur choisi ");
					System.out.println("donner maintenant la couleur du piquet du niveau 1 :");
					str=s.next();
					c=str.charAt(0);
					c_maj=Character.toUpperCase(c);
					if(c_maj=='B'  )
						{n=new noeud(c_maj,1,2);
						l.add(n);
						test=true;
						x=1;}
				
						else if(c_maj=='V')
							{n=new noeud(c_maj,1,3);
							l.add(n);
							test=true;
							x=1;}
					
							else if(c_maj=='J')
								{n=new noeud(c_maj,1,4);
								l.add(n);
								test=true;
								x=1;}
								else if(c_maj=='*')
									testQuitter=true;
							
									else {
									System.out.println("Au niveau 1 vous pouvez choisir que les couleurs suivantes : B, V,J");
									}
					debut=System.currentTimeMillis();
				}
				debut=System.currentTimeMillis();
				test=false;
				while (test==false && testQuitter==false && debut<end) {
					System.out.println("\nVous avez "+((end-debut)/1000)+" secondes pour terminer ce stage du jeu");
					System.out.println("Au deuxiéme niveau, Vous pouvez choisir les couleurs des piquets suivant: Noir : N, Argent: A:");
					System.out.println("Vous devez écrire la premiére lettre de la coulaur choisi ");
					System.out.println("donner la couleur du piquet du niveau 2 ");
					str=s.next();
					c=str.charAt(0);
					c_maj=Character.toUpperCase(c);
					if(c_maj=='N' )
						{n=new noeud(c_maj,2,5);
						l.add(n);
						test=true;
						x=2;}
						else if(c_maj=='A')
							{n=new noeud(c_maj,2,6);
							l.add(n);
							test=true;
							x=2;}
							else if(c_maj=='*')
								testQuitter=true;
								else {System.out.println("Au niveau 2 vous pouvez choisir que les couleurs suivantes : N, A");
								}
					debut=System.currentTimeMillis();
				}
				debut=System.currentTimeMillis();
				test=false;
				while (test==false && testQuitter==false && debut<end) {
					System.out.println("\nVous avez "+((end-debut)/1000)+" secondes pour terminer ce stage du jeu");
					System.out.println("Au troisiéme niveau, Vous pouvez choisir les couleurs des piquets suivant : Marron : M, Gris: G:");
					System.out.println("Vous devez écrire la premiére lettre de la coulaur choisi ");
					System.out.println("donner la couleur du piquet du niveau 3");
					str=s.next();
					c=str.charAt(0);
					c_maj=Character.toUpperCase(c);
					if(c_maj=='M' )
						{n=new noeud(c_maj,3,7);
						l.add(n);
						test=true;
						x=3;}
						else if(c_maj=='G')
							{n=new noeud(c_maj,3,8);
							l.add(n);
							test=true;
							x=3;}
							else if(c_maj=='*')
								testQuitter=true;
								else {System.out.println("Au niveau 3 vous pouvez choisir que les couleurs suivantes : M, G");
								}
					debut=System.currentTimeMillis();
				}
				debut=System.currentTimeMillis();
				test=false;
				while (test==false && testQuitter==false && debut<end) {
					System.out.println("\nVous avez "+((end-debut)/1000)+" secondes pour terminer ce stage du jeu");
					System.out.println("Au dernier niveau, Vous pouvez choisir les couleurs des piquets suivant : Doré : D:");
					System.out.println("Vous devez écrire la premiére lettre de la coulaur choisi ");
					System.out.println("donner la couleur du piquet du niveau 4:");
					str=s.next();
					c=str.charAt(0);
					c_maj=Character.toUpperCase(c);
					if(c_maj=='D' )
						{n=new noeud(c_maj,4,9);
						l.add(n);
						test=true;
						x=4;}
						else if(c_maj=='*') {
							testQuitter=true;}
							else {System.out.println(" A ce niveau, vous devez soit tapez D soit * pour quitter ");
							}
					debut=System.currentTimeMillis();
				}
				
		
		if (debut>end) {
			System.out.println("VOTRE TEMPS EST ÉCOULÉ ! ");
		}
		System.out.println("\n");
		System.out.println("Votre chemin est : \n         "+ l);
		int dis=MontagneBrunchAndBound.calculDistance(l);

		int disMinimale= MontagneBrunchAndBound.distanceT;
		int Score=100;
		int ScoreF;
		System.out.println("Votre distance parcourue est : "+dis);
		if(dis==disMinimale) {
			System.out.println("\n");
			System.out.println("Vous avez choisit le chemin minimale,"
			+ "\n          votre Score est egal a 100,"
			+ "\n                 BRAVO! ");}
			else if(dis==0) {
				System.out.println("\n");
				System.out.println("Vous avez échoué,"
				+ "\n          votre Score est egal a 0,"
				+ "\n                 À LA PROCHAINE.");}
				else { 
					ScoreF=Score-Math.abs(dis-disMinimale);
					System.out.println("\n");
				System.out.println("Vous avez échoué,"
				+ "\n            Votre Score est "+ ScoreF
				+"\n                À LA PROCHAINE.");}

		System.out.println("\n");
		System.out.println("le chemin minimale est :\n         "+ MontagneBrunchAndBound.cheminFinal);
		System.out.println("la distance minimale est : "+disMinimale);

		}

}