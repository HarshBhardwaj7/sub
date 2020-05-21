import java.text.SimpleDateFormat;
import java.text.ParseException;
import java.util.Date;
import java.util.Scanner; 
import java.time.*;
import java.util.Calendar;
import java.util.ArrayList; 
public class Main{

     public static void main(String []args)throws ParseException{
         Main m =new  Main();
            m.solution();
        
     }
      public void solution() throws ParseException
       {
           Scanner sc = new Scanner(System.in);
        SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
        Calendar c = Calendar.getInstance();
        
       
        
        System.out.print("Enter Number of Date-val Entries: ");
        int numDateVal = sc.nextInt();
        sc.nextLine();
        
        ArrayList<DatePair> datePairs = new ArrayList<DatePair>();
        
        while(numDateVal>0){
            System.out.print("Enter Date in format(YYYY-MM-DD): ");
            String dt1 = sc.nextLine();
            c.setTime(sdf.parse(dt1));
            Date date = c.getTime();
            
            System.out.print("Enter Value(in range 0-1,000,000): ");
            int val = sc.nextInt();
              sc.nextLine();
            Main.DatePair datePair = new Main.DatePair(date,val);
            datePairs.add(datePair);
            numDateVal--;
        }
       
      
         ArrayList<DatePair> filledDatePairs = getFilledArray(datePairs);
         
         for(int i=0;i<filledDatePairs.size();i++){
            System.out.println(filledDatePairs.get(i).date+" : "+filledDatePairs.get(i).val);
         }
       }
       
     
     public  ArrayList<DatePair>   getFilledArray(ArrayList<DatePair> datePairs){
         Calendar c = Calendar.getInstance();
         ArrayList<DatePair> filledDatePairs = new ArrayList<DatePair>();
         for(int i=0;i<datePairs.size()-1;i++){
            filledDatePairs.add(datePairs.get(i));
            long diff = getDiffrence(datePairs.get(i).date,datePairs.get(i+1).date);
            if(diff>1){
                int valDiff = datePairs.get(i+1).val-datePairs.get(i).val;
                long valDelta = valDiff/diff;
                c.setTime(datePairs.get(i).date);
                int initialVal = datePairs.get(i).val;
                for(int j=0;j<diff-1;j++)
                {
                    c.add(Calendar.DATE, 1);
                    initialVal+=valDelta;
                    filledDatePairs.add(new DatePair(c.getTime(),initialVal));
                }
            }
         }
         filledDatePairs.add(datePairs.get(datePairs.size()-1));
         return filledDatePairs;
     }
     
     public  long getDiffrence(Date date1,Date date2){
         return (date2.getTime()-date1.getTime())/1000/60/60/24;
     }
     
     public class DatePair{
         public Date date;
         public int val;
         DatePair(Date date, int val){
             this.date = date;
             this.val = val;
         }
     }
     
}