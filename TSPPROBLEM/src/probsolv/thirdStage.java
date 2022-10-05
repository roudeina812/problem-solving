package probsolv;

import java.util.*;

public class thirdStage {

    public static boolean[][] dpTable;
    public static Random rand = new Random();
    public static List<Integer> numbers=new ArrayList<>();
    public static int sum;
    //this method read the sum value and numbers from keyboard
    //numbers are given as String then we use split to extract numbers
    public static Scanner s = new Scanner(System.in);
    public static boolean choose (ArrayList<Integer>user)
    {
        boolean result;
        System.out.println("choose the values you want to put in sack");
        try {
            result=true;
            String expression = s.next();
            String[] tabNumbers = expression.split(",");
            for (String x : tabNumbers) {
                //convert from string to Integer
                Integer val = Integer.valueOf(x);
                //or
                //int val=Integer.parseInt();
                if (val < 0 ) //numbers must be positives
                    throw new Exception("All the set numbers must be  positives");
                if(!thirdStage.verifie(val))
                    throw new Exception("values not possible !!!");
                user.add(val);


            }
            System.out.print("exist in sack: [ ");
            for(int i=0 ;i< user.size()-1 ; i++)
            {
                System.out.print(user.get(i) +",");

            }
            System.out.print(user.get(user.size()-1)+ " ] ");
            System.out.println("\n");
        } catch (Exception e) {
            System.out.println(e.getMessage());
            result = false;
        }
        return result;
    }
    //fonction pour donner liste des nombres par hasard
    public static void NumList (int a)
    {
        int upperbound = 19;
        for(int i=0; i<a ; i++)
        {
            int random = rand.nextInt(upperbound)+1;
            numbers.add(random);
        }

    }
    //fonction pour donner somme par hasard
    public static void sumSelect ()
    {
        int upperbound = 25;
        sum=rand.nextInt(upperbound)+1;
    }
    // fonction pour saisi les nombres et le sum
    public static void select ()
    {

        sumSelect();
        NumList(6);

        int max=0;
        for(int i=0;i< numbers.size();i++)
        {
            if(max< numbers.get(i))
                max= numbers.get(i);
        }
        if(max>sum)
        {numbers.clear();
            sum=0;
            select();}
    }
    //function pour donner max des listes

    public static int MaxSolutuion (List<ArrayList<Integer>>sol)
    {
        int max =0;
        ArrayList<Integer> maxuser = new ArrayList<>();
        int a=0;
        for(int i=0;i< sol.size();i++)
        {
            for (int j=0;j<sol.get(i).size();j++)
            {
                a+=sol.get(i).get(j);
            }
            maxuser.add(a);
            a=0;
        }
        for(int i=0;i<maxuser.size();i++)
        {
            if (maxuser.get(i)>max)
                max=maxuser.get(i);
        }
        return max ;
    }

    //fonction pour determiner le score de joueur
    public static double scorecalcul (ArrayList<Integer>user ,List<ArrayList<Integer>>sol)
    {
        double score=70;
        int a = MaxSolutuion(sol);
        int som = 0;
        if(!contains(user,sol))
            return score;
        for(int i=0 ;i< user.size();i++)
            som+= user.get(i);
        if (som==a)
            score=score*2;
        else
        {if (som>(a/2))
            score=score+score*0.75;
        else
        { if (som==(a/2))
            score=score+score/2;
        else
            score=score+score*0.25;
        }
        }
        return score;

    }
    // fonction pour verifier si les nombres saisis par le joueur existe dans la liste ou non
    public static boolean verifie (int val)
    {
        boolean verif=false;
        int i=0;
        while(!verif&&i<numbers.size())
        {
            if (val==numbers.get(i))
                verif=true;
            else
                i++;
        }
        return verif;
    }
    //fonction pour verifier si la solution proposé par le joueur existe dans la liste des solutuions ou non
    public static boolean contains (ArrayList<Integer>user , List<ArrayList<Integer>> sol)
    {
        boolean contain=false;
        int i=0;
        while(i< sol.size()&&!contain)
        {
            if(sol.get(i).equals(user))
                contain=true;
            else
                i++;
        }

        return contain;
    }
    //fontion pour changer l'ordre de tableau
    public static void translation ()
    {
        int fin = numbers.get(0);
        for(int i=1 ; i< numbers.size();i++)
        {
            numbers.set(i-1,numbers.get(i));
        }
        numbers.set(numbers.size()-1,fin);
    }
    //fontion pour remplir la matrice dpTable par true ,false
    public static void fillDpTable()
    {
        dpTable=new boolean[numbers.size()+1][sum+1];
        //The first line of the dpTable matrix is false except the first column
        //from an empty set we can not find sum>0
        for(int i=1;i<=sum;i++)
            dpTable[0][i]=false;
        //the fist column is True
        //zero is on all subsets
        for(int i=0;i<dpTable.length;i++)
            dpTable[i][0]=true;
        //fill the others cases in the dpTable matrix
        for(int i=1;i<dpTable.length;i++)
            for(int j=1;j<dpTable[i].length;j++)
            {
                if(j<numbers.get(i-1)) //we save the previous found sums
                    dpTable[i][j]=dpTable[i-1][j];
                else
                if(dpTable[i-1][j])
                    dpTable[i][j]=true;
                else
                    dpTable[i][j]=dpTable[i-1][j-numbers.get(i-1)];
            }
    }


    // fonction retourne arraylist d'un solution
    public static ArrayList<Integer> printSolution(int i ,int j){

        ArrayList<Integer> S= new ArrayList<>();
        while(i>0&&j>0)
        {
            if(dpTable[i][j]&&dpTable[i-1][j])
                //if the current row of the column j is true and the row above is also true
                //so we just go to the line above
                i--;
            else
            {
                S.add(numbers.get(i-1));
                j-=numbers.get(i-1);
                i--;
            }

        }
        return S;
    }
    //fontion pour verifier si une arraylist existe dans la liste des solutions ou non
    public static boolean trouve (ArrayList<Integer> l,List<ArrayList<Integer>> sol)
    {
        boolean contient = false;
        for (int i=0 ;i< sol.size();i++)
        {
            if(sol.get(i).equals(l))
                contient =true ;

        }
        return contient;
    }
    //fonction pour afficher les arraylist
    public static void AfficheArray (ArrayList<Integer> AR)
    {
        System.out.print(" Solution : [ ");
        for(int i=0 ;i< AR.size()-1 ; i++)
        {
            System.out.print(AR.get(i) +",");

        }
        System.out.print(AR.get(AR.size()-1)+ " ] ");
        System.out.println("\n");
    }
    // fonction de DP recursive  : calcul tous les solutions de chaque colonne pour la matrice dpTable
    public static void solve (int fin, List<ArrayList<Integer>> sol )
    {
        if(fin== numbers.size())
        {
            System.out.print("come on !! ");
        }
        else
        {
            ArrayList<Integer> l = new ArrayList<>();

            int j = sum;
            int i = numbers.size();
            fillDpTable();
            while (j > 0)
            {
                if (dpTable[i][j])
                {
                    l = thirdStage.printSolution(i, j);
                    Collections.sort(l);
                    if(!trouve(l,sol)) {  sol.add(l); }
                    j--;

                } else j--;

            }
            translation();
            fin ++;
            solve(fin, sol);
        }
    }



    public static List<Integer> getNumbers() {
        return numbers;
    }

    public static int getSum() {
        return sum;
    }



    public void start(){

        Scanner s = new Scanner(System.in);
        int fin =0;
        int a=0;
        double score=70;
        boolean result;
        long end = System.currentTimeMillis()+60000;
        long start=System.currentTimeMillis();

        System.out.println("La troisième partie du jeu va commencer maintenant !");
        System.out.println("Dans ce troisième stage du jeu, vous avez un sac qui a une capacité précis et vous avez des boules d'or avec un poids ");
        System.out.println("vous devez choisir le maximum des boules mais il ne faux pas dépasser la capacité de sac ");
        List<ArrayList<Integer>> sol = new ArrayList<>();
        // saisie la liste et le poids
        thirdStage.select();
        // resolu tous les solutions
        thirdStage.solve(fin,sol);
        //declarer liste du jouer
        ArrayList<Integer> user = new ArrayList<>();
        //----------------------------------------------------------------
        do
        {
            //afficher la liste des boules
            System.out.print("les poids des boules d'or : [ ");
            for (int i=0 ;i<thirdStage.getNumbers().size()-1;i++)
            { System.out.print(thirdStage.getNumbers().get(i)+ " , ");}
            System.out.print(thirdStage.getNumbers().get(thirdStage.getNumbers().size()-1)+"]");
            System.out.println("\n");
            //afficher le poids du sac
            System.out.println("la capacité de ton sac = "+thirdStage.getSum());
            System.out.println("\n");
            System.out.println("\nIl vous reste "+((end-start)/1000)+" secondes");
            user.clear();  a=0;

            // assurer que les nombres saisi sont positifs et existant dans la la liste des poids
            do {result=thirdStage.choose(user);}while(!result);

            //------------------------------------------------------------------
            Collections.sort(user);
            System.out.println("-------------------------------------------------------------------" );
            if(thirdStage.contains(user,sol))
                System.out.println("bien : solution existante :) ");
            else
                System.out.println("malheureusement : solution non existante :( ");
            //calcul de score
            score=thirdStage.scorecalcul(user,sol);
            //pour calcluer la poids max de solution choisi par le joueur
            for (int j=0;j<user.size();j++)  {    a+= user.get(j);  }
            start=System.currentTimeMillis();
        } while(end>start && a!=thirdStage.MaxSolutuion(sol));

        if(end<=start)
            System.out.println("Temps ecoulé :( ");
        else if (a==thirdStage.MaxSolutuion(sol))
            System.out.println("Bravo !! c'est la meuilleure solution ");

        System.out.println("-------------------------------------------------------------------" );
        System.out.println("Les solutions possibles  :");
        System.out.println("-------------------------------------------------------------------" );
        for (int b = 0; b <= sol.size() - 1; b++)
        {
            thirdStage.AfficheArray(sol.get(b));
        }
        System.out.println("-------------------------------------------------------------------" );
        System.out.println("Votre score : " + score);
        System.out.println("-------------------------------------------------------------------" );

    }

}

