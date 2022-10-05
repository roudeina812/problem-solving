package probsolv;

import java.util.HashSet;
import java.util.Random;
import java.util.Scanner;
import java.util.Set;

public class fisrtStage {

	

	    static Set<Integer> l;
	    static Set<Set<Integer>> results;
	    static int s;

	    public static void generateL()
	    {
	        l=new HashSet<>();
	        Random r = new Random();
	        int j;
	        for(int i=0;i<7;i++)
	        {
	            j=Math.abs((r.nextInt())%30)+1;
	            l.add(j);
	        }
	    }

	    public static int selectSum()
	    {
	        Random r=new Random();
	        s=Math.abs(r.nextInt()%50);
	        return s;

	    }

	    public static Set<Integer> getNextNumbers(Set<Integer> result,int rest)
	    {
	        Set<Integer> next=new HashSet<>();
	        for (Integer i : l)
	            if (!result.contains(i) && i<=rest)
	                next.add(i);
	        return next;
	    }

	    public static void getSolutions(int s,int sum,Set<Integer> currentResult)
	    {
	        if(sum==s)
	            results.add(new HashSet<>(currentResult));
	        else if (sum<s)
	        {
	            Set<Integer> next=getNextNumbers(currentResult,s-sum);
	            for(Integer i : next)
	            {
	                if((sum+i)<=s)
	                {
	                    currentResult.add(i);
	                    getSolutions(s,sum+i,currentResult);
	                    currentResult.remove(i);
	                }
	            }
	        }
	    }

	    public static void generateResults()
	    {
	        Set<Integer> currentResult=new HashSet<>();
	        results = new HashSet<>();
	        for (Integer i : l)
	        {
	            currentResult.add(i);
	            getSolutions(s,i,currentResult);
	            currentResult.remove(i);
	        }
	    }



	    public int start ( Set<Set<Integer>> result ){
           
	        do
	        {
	            generateL();
	            selectSum();
	            generateResults();
	        }
	        while(results.size()<3);

	        System.out.println("La première partie du jeu va commencer maintenant !");
	        System.out.println("Dans ce premier stage du jeu, on doit afficher une liste des nombres ainsi qu'un autre nombre.\n   Vous devez chercher parmi la liste les ensembles des nombres qui font la somme de nombre donné avant que le temps ne soit écoulé :\n    Si vous voulez quitter  tapez * :\n");
	        System.out.println("List of numbers :"+l);
	        System.out.println("Sum = "+s);


	       
	        int score=0;
	        long end = System.currentTimeMillis()+60000;
	        long start=System.currentTimeMillis();
	        do
	        {
	            System.out.println("\nIl vous reste "+((end-start)/1000)+" secondes");
	            System.out.print("    ->votre reponse : ");
	            Scanner s = new Scanner(System.in);
	            String[] input = (s.next()).split(",");
	            if (input[0].equals("*"))
	                break;
	            Set<Integer> guess = new HashSet<>();
	            for (String str : input)
	                guess.add(Integer.parseInt(str));
	            System.out.println("    -> votre reponse est : " +result.contains(guess));
	            if(result.contains(guess))
	            {
	                if(!results.add(guess))
	                    System.out.println("        Mais dejà saisi !!! ");
	                else
	                    score+=10;
	            }
	            start=System.currentTimeMillis();
	        }
	        while(end>start || result.size()== results.size());

	        if(end<=start)
	            System.out.println("\n  TEMPS ECOULE !!!\n Votre score : "+score+" points");
	        else if (results.size()== results.size())
	            System.out.println("\n    BRAVO !! vous avez saisi toutes les solutions\\n Votre score : "+score+" points");
	        else
	            System.out.println("AU REVOIR");
	        
	        return score ;

	    }
	}





