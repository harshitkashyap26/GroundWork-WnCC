//The following code has been made in Java using BlueJ
import java.util.*;
import java.io.*;
public class Time
{
    public static void main(String[] args) throws Exception
    {
        File timestat=new File("timestat.txt");
        String s;
        int c=0,run=0,runR=0,runU=0,runS=0;
        double avgR=0,avgU=0,avgS=0,stdR=0,stdU=0,stdS=0;
        
        /*The storage and calculation of real, user and sys averages and standard deviation has been done using the above declared variables respectively.
        Have tried my best not to use any extra variable(s) to calculate those. s is to store the current line of the text file getting read. c is a 
        counter variable to store how many lines have been executed of the text file. run to store the number of runs. runR,runU,runS to store the number
        of runs falling between average and std deviation of real,user,system time respectively  */
        
        Scanner sc1=new Scanner(timestat); //'Scanning' text file for first time to calculate averages
        while(sc1.hasNextLine())
        {
           s=sc1.nextLine();
           switch(c%4) //Each run has 4 lines- First-empty line, Second,Third,Fourth line- Real,User,Sys time
           {
               case 0:
               run++; //Using empty line as a sign of completion of 1 run
               break;
               case 1:
               avgR+=Double.parseDouble(s.substring(10,15));  //Extraction of real time(seconds) from string and summing it up all in avgR
               break;
               case 2:
               avgU+=Double.parseDouble(s.substring(10,15));
               break;
               case 3:
               avgS+=Double.parseDouble(s.substring(6,11));
           }
           c++;         
        } 
        avgR=Math.round(avgR*1000.0)/1000.0/run;  //The sum stored in avgR is now divided by the no of runs to get the actual average.
        avgU=Math.round(avgU*1000.0)/1000.0/run;  //Math.round has been used to counter the 'precision loss in data' which occures while operating on double and float
        avgS=Math.round(avgS*1000.0)/1000.0/run;
        System.out.println("Number of runs: "+run);
        System.out.println("Average Time Statistics");
        System.out.println("Real: 0m"+avgR+"s   User: 0m"+avgU+"s   Sys: 0m"+avgS+"s");
        Scanner sc2=new Scanner(timestat); //Second scanning to find standard deviation
        while(sc2.hasNextLine())
        {
            s=sc2.nextLine();
            switch(c%4)
            {
                case 1:
                stdR+=Double.parseDouble(s.substring(10,15))*Double.parseDouble(s.substring(10,15)); //Storing sum of squares of real time in stdR
                break;
                case 2:
                stdU+=Double.parseDouble(s.substring(10,15))*Double.parseDouble(s.substring(10,15));
                break;
                case 3:
                stdS+=Double.parseDouble(s.substring(6,11))*Double.parseDouble(s.substring(6,11));
            }
            c++;
        }
        stdR=Math.sqrt(stdR/run-avgR*avgR); //Finding actual std deviation using formula SD= square root of (mean of squares- square of mean)
        stdU=Math.sqrt(stdU/run-avgU*avgU);
        stdS=Math.sqrt(stdS/run-avgS*avgS);
        System.out.println("Standard Deviation of Time statistics");
        System.out.println("Real: 0m"+stdR+"s   User: 0m"+stdU+"s   Sys: 0m"+stdS+"s");
        Scanner sc3=new Scanner(timestat); //Scanning for the third time
        while(sc3.hasNextLine())
        {
            s=sc3.nextLine();
            switch(c%4)
            {
                case 1:
                if(Double.parseDouble(s.substring(10,15))>(avgR-stdR) && Double.parseDouble(s.substring(10,15))<(avgR+stdR))
                runR++; //Finding required no of runs which lie between (avg-std) to (avg+std)
                break;
                case 2:
                if(Double.parseDouble(s.substring(10,15))>(avgU-stdU) && Double.parseDouble(s.substring(10,15))<(avgU+stdU))
                runU++;
                break;
                case 3:
                if(Double.parseDouble(s.substring(6,11))>(avgS-stdS) && Double.parseDouble(s.substring(6,11))<(avgS+stdS))
                runS++;
            }
            c++;
        }
        System.out.println("Number of runs within average-standard deviation to average+standard deviation");
        System.out.println("Real: "+runR+"   User: "+runU+"   Sys: "+runS);
    }
}
    

        