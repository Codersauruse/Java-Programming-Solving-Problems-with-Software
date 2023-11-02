import edu.duke.*;
import org.apache.commons.csv.*;
import java.util.*;
import java.io.File;
/**
 * Write a description of babyBirth here.
 * 
 * @author (your name) 
 * @version (a version number or a date)
 */
public class babyBirth {
    
    public void totalBirth(FileResource fr){
    int totalBirths = 0;
    int numOfBoys = 0;
    int numOfGirls = 0;
    for(CSVRecord r :fr.getCSVParser(false)){
        totalBirths += Integer.parseInt(r.get(2));
        if(r.get(1).equals("M")){
            numOfBoys += 1;
            
        }
        else{
        
            numOfGirls += 1;
        
        }
    
    }
        System.out.println("Total births :"+totalBirths);
        System.out.println("Number of names of boys :"+numOfBoys);
        System.out.println("Number of names of Girls :"+numOfGirls);
        
    
    }
    public void tester(){
        //FileResource fr = new FileResource();
        //totalBirth(fr);
        //System.out.println("Rank is "+getRank(2012,"Mason","M"));
       // System.out.println("Rank is "+getRank(1971,"Frank","M"));
        //System.out.println("Name is "+getName(1982,450,"M"));
        //whatIsNameInYear("Owen",1974,"M",2014);
        //System.out.println("the Highest rank year is :"+yearOfHighestRank("Mich","M"));
        //System.out.println("The Average rank is :"+getAverageRank("Robert","F"));
        System.out.println("The totalBirth is : "+getTotalBirthsRankedHigher(1990,"Drew","M"));
    }
    public int getRank(int year,String name,String gender){
        int rank = 0;
        int flag = 0;
        String filename = "yob"+year+".csv";
        FileResource fr = new FileResource(filename);
        for(CSVRecord r : fr.getCSVParser(false)){
            if(r.get(1).equals(gender)){
                rank += 1;
                if(r.get(0).equals(name)){
                    flag = 1;
                    break;                
                }
            }
        }
        if(flag == 1){
            return rank;
        
        }
        else{
            return -1;
        
        }
    }
    public int GetRank(File filename,String name,String gender){
        int rank = 0;
        int flag = 0;
         FileResource fr = new FileResource(filename);
        for(CSVRecord r : fr.getCSVParser(false)){
            if(r.get(1).equals(gender)){
                rank += 1;
                if(r.get(0).equals(name)){
                    flag = 1;
                    break;                
                }
            }
        }
        if(flag == 1){
            return rank;
        
        }
        else{
            return -1;
        
        }   
    
    
    }
    
    public String getName(int year,int rank,String gender){
        int i = 1;
        String output = "No Name";
        String filename = "yob"+year+".csv";
        FileResource fr = new FileResource(filename);
        for(CSVRecord r :fr.getCSVParser(false)){
            if(r.get(1).equals(gender)){
            
                if(i == rank){
                output = r.get(0);
                
                }
                i++;
            
            }
        
        
        }
    
        return output;
    
    }
    public void whatIsNameInYear(String name,int year,String gender,int newyear){
        String pronoun = "";
        int rank = getRank(year,name,gender);
        String newName = getName(newyear,rank,gender);
        if(gender.equals("M")){
          pronoun = "he";
        
        }
        else{
            pronoun = "she";
        
        }
        System.out.println(name + " born in "+year+ " would be "+ newName +" if "+ pronoun +" was born in "+newyear);
    
    
    }
    public int yearOfHighestRank(String name,String gender){
    int highestRank = 1000;
    File currFile = null;
    DirectoryResource dr =  new DirectoryResource();
    for( File f : dr.selectedFiles()){
        int currentRank = GetRank(f,name,gender);
        if(currentRank == -1){
            return -1;
        
        }
        if(currentRank < highestRank){
           highestRank = currentRank;
           currFile = f;
        }
        
    
    }
    String fileName = currFile.getName();
    int startindex = fileName.indexOf("yob");
    int stopindex = fileName.indexOf(".");
    int year = Integer.parseInt(fileName.substring(startindex + 3,stopindex));
    return year;
    
    }
    public double getAverageRank(String name,String gender){
    double count = 0;
    double sumOfRanks = 0.0;
    DirectoryResource dr =  new DirectoryResource();
    for( File f : dr.selectedFiles()){
        int currentRank = GetRank(f,name,gender);
            sumOfRanks += currentRank;
            count += 1;
        }
    return sumOfRanks/count;
}
    public int getRank(String filename,String name,String gender){
        int rank = 0;
        int flag = 0;
        FileResource fr = new FileResource(filename);
        for(CSVRecord r : fr.getCSVParser(false)){
            if(r.get(1).equals(gender)){
                rank += 1;
                if(r.get(0).equals(name)){
                    flag = 1;
                    break;                
                }
            }
        }
        if(flag == 1){
            return rank;
        
        }
        else{
            return -1;
        
        }   
    
    
    }
    public int getTotalBirthsRankedHigher(int year,String name,String gender){
        String filename = "yob"+year+".csv";
        int rank = getRank(filename,name,gender);
        int totalBirths = 0;
        int i = 1;
        if(rank == 0){
            return 0;
        }
        FileResource fr = new FileResource(filename);
        for(CSVRecord r : fr.getCSVParser(false)){
            if(r.get(1).equals(gender)&& i < rank){
                totalBirths += Integer.parseInt(r.get(2));
                i++;
                
            }
        
    
    
    
    }
    return totalBirths;
}
    
}
    
    
    
      
    


