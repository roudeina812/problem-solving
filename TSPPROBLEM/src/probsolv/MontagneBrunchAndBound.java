package probsolv;



import java.util.ArrayList;
import java.util.HashMap;
import java.util.Iterator;
import java.util.LinkedList;
import java.util.List;
import java.util.Map;
import java.util.Queue;

public class MontagneBrunchAndBound {
    public static int[][] graphe =new int[9][9];
    public static int nbpiquet=9;
    public static int distanceT=0;
    public static noeud piquetDepart= new noeud('R',0,1);
    public static noeud piquetFinal= new noeud('D',4,9);
    public static List<Character> piquetC =new ArrayList<Character>();
    public static List<noeud> cheminFinal;
    public static Queue<noeud> fileAttente=new LinkedList<>();
    public static List<noeud> listenoeud=new ArrayList<>();
    public static Map<noeud,List<noeud>>  arbre= new HashMap<>();
    
    public static void genererCouleur() {
    	piquetC.add('R');
    	piquetC.add('B');
    	piquetC.add('V');
    	piquetC.add('J');
    	piquetC.add('N');
    	piquetC.add('A');
    	piquetC.add('M');
    	piquetC.add('G');
    	piquetC.add('D');
    }
    public static void genererGraph()
    {

    	graphe[0][0]=7;
    	graphe[0][1]=27;
    	graphe[0][2]=19;
    	graphe[0][3]=23;
    	graphe[0][4]=4;
    	graphe[0][5]=50;
    	graphe[0][6]=41;
    	graphe[0][7]=54;
    	graphe[0][8]=4;
    	
    	graphe[1][0]=4;
    	graphe[1][1]=7;
    	graphe[1][2]=10;
    	graphe[1][3]=50;
    	graphe[1][4]=1;
    	graphe[1][5]=35;
    	graphe[1][6]=62;
    	graphe[1][7]=28;
    	graphe[1][8]=43;
    	
    	graphe[2][0]=40;
    	graphe[2][1]=3;
    	graphe[2][2]=85;
    	graphe[2][3]=26;
    	graphe[2][4]=30;
    	graphe[2][5]=29;
    	graphe[2][6]=39;
    	graphe[2][7]=46;
    	graphe[2][8]=5;
    	
    	graphe[3][0]=87;
    	graphe[3][1]=2;
    	graphe[3][2]=14;
    	graphe[3][3]=57;
    	graphe[3][4]=19;
    	graphe[3][5]=27;
    	graphe[3][6]=49;
    	graphe[3][7]=53;
    	graphe[3][8]=44;
    	
    	graphe[4][0]=1;
    	graphe[4][1]=65;
    	graphe[4][2]=23;
    	graphe[4][3]=27;
    	graphe[4][4]=51;
    	graphe[4][5]=10;
    	graphe[4][6]=7;
    	graphe[4][7]=37;
    	graphe[4][8]=28;
    	
    	graphe[5][0]=39;
    	graphe[5][1]=18;
    	graphe[5][2]=32;
    	graphe[5][3]=1;
    	graphe[5][4]=8;
    	graphe[5][5]=45;
    	graphe[5][6]=6;
    	graphe[5][7]=23;
    	graphe[5][8]=20;
    	
    	graphe[6][0]=29;
    	graphe[6][1]=47;
    	graphe[6][2]=2;
    	graphe[6][3]=40;
    	graphe[6][4]=18;
    	graphe[6][5]=30;
    	graphe[6][6]=18;
    	graphe[6][7]=47;
    	graphe[6][8]=12;
    	
    	graphe[7][0]=7;
    	graphe[7][1]=10;
    	graphe[7][2]=38;
    	graphe[7][3]=40;
    	graphe[7][4]=2;
    	graphe[7][5]=5;
    	graphe[7][6]=21;
    	graphe[7][7]=17;
    	graphe[7][8]=60;
    	
    	graphe[8][0]=38;
    	graphe[8][1]=29;
    	graphe[8][2]=54;
    	graphe[8][3]=1;
    	graphe[8][4]=46;
    	graphe[8][5]=14;
    	graphe[8][6]=40;
    	graphe[8][7]=32;
    	graphe[8][8]=4;
    	
    }
    
    public static void afficherGraphe() {
        for (int i = 0; i < nbpiquet; i++) {

            for (int j = 0; j < nbpiquet; j++) {
                System.out.print(graphe[i][j] + "\t");
            }
            System.out.println();
        }
    }

    public static noeud getMinFils(Queue<noeud> fileAttente, noeud n)
    {
        Iterator<noeud> it=fileAttente.iterator();
        noeud minNoeud = it.next();
        while(it.hasNext())
        {
            noeud no=it.next();
            minNoeud =no.comparer(n,minNoeud);
        }
        return minNoeud;
    }
    
    public static void remplirListNoeud() {
    	listenoeud.add(piquetDepart);
    	for(int i=1; i<8; i++) {
    		noeud n= new noeud(piquetC.get(i),1,(i+1));
    	}
    	listenoeud.add(piquetFinal);
    }
    
    public static ArrayList<noeud> remplirArbre() {
    	ArrayList l=new ArrayList<noeud>();
    		noeud n2=new noeud('B',1,2);
    		noeud n3=new noeud('V',1,3);
    		noeud n4=new noeud('J',1,4);
    		noeud n5=new noeud('N',2,5);
    		noeud n6=new noeud('A',2,6);
    		noeud n7=new noeud('M',3,7);
    		noeud n8=new noeud('G',3,8);
    		l.add(piquetDepart);
    		l.add(n2);
    		l.add(n3);
    		l.add(n4);
    		l.add(n5);
    		l.add(n6);
    		l.add(n7);
    		l.add(n8);
    		l.add(piquetFinal);
    		List<noeud> fils1=new ArrayList<>();
    		fils1.add(n2);
    		fils1.add(n3);
    		fils1.add(n4);
    		List<noeud> fils2=new ArrayList<>();
    		fils2.add(n5);
    		fils2.add(n6);
    		List<noeud> fils3=new ArrayList<>();
    		fils3.add(n5);
    		fils3.add(n6);
    		List<noeud> fils4=new ArrayList<>();
    		fils4.add(n5);
    		fils4.add(n6);
    		List<noeud> fils5=new ArrayList<>();
    		fils5.add(n7);
    		fils5.add(n8);
    		List<noeud> fils6=new ArrayList<>();
    		fils6.add(n7);
    		fils6.add(n8);
    		List<noeud> fils7=new ArrayList<>();
    		fils7.add(piquetFinal);
    		List<noeud> fils8=new ArrayList<>();
    		fils8.add(piquetFinal);
    		arbre.put(piquetDepart, fils1);
    		arbre.put(n2, fils2);
    		arbre.put(n3, fils3);
    		arbre.put(n4, fils4);
    		arbre.put(n5, fils5);
    		arbre.put(n6, fils6);
    		arbre.put(n7, fils7);
    		arbre.put(n8, fils8);
    		return l;
    }
    
    public static List<noeud> getfilsnoeudpossible(noeud n){
    	List<noeud> listefils=new ArrayList<>();
    	for(int i=0;i< arbre.get(n).size();i++)
    		listefils.add(arbre.get(n).get(i));
    	for(int i=0; i<listefils.size();i++) {
    		noeud no =listefils.get(i);
    		no.setDistance(graphe[n.getNombre()-1][no.getNombre()-1]);
    	}
        return listefils;
    }
    
    public static int calculDistance(ArrayList<noeud> parcourChoisi) { 
    	int distanceparcourue=0; 
    		for(int i=0;i<=parcourChoisi.size()-2;i++) 
    			distanceparcourue+= graphe[(parcourChoisi.get(i).getNombre())-1][(parcourChoisi.get(i+1).getNombre())-1];
    	return distanceparcourue;
    }
    
    public static void resoudremontagne(){
        noeud noeudcourant =piquetDepart;
        cheminFinal=new ArrayList<noeud>();
        fileAttente.offer(noeudcourant);
        cheminFinal.add(noeudcourant);
        while(!fileAttente.isEmpty()&& !cheminFinal.contains(piquetFinal))
        {	
            List<noeud> lstChilderen= getfilsnoeudpossible(noeudcourant);
            if(!lstChilderen.isEmpty()) {
            	fileAttente.remove(noeudcourant);
            	fileAttente.addAll(lstChilderen);
                noeudcourant=getMinFils(fileAttente, noeudcourant);
            }
            for(int i=0;i<cheminFinal.size();i++) 
            	if(noeudcourant.getNiveau()==cheminFinal.get(i).getNiveau())
            		cheminFinal.remove(i);
            cheminFinal.add(noeudcourant);
        }
        for(int i=1;i<cheminFinal.size();i++)
        	distanceT+=cheminFinal.get(i).getDistance();
    } 
}