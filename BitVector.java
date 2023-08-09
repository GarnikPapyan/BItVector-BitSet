import java.util.Scanner;
import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.util.Arrays;

public class BitVector  {
     
    public static void main(String[] args) {
        Scanner scanner =  new Scanner(System.in);
        System.out.println("Plz enter your bit size");
        int bitSize = scanner.nextInt();
        if(bitSize<0){
            throw new IllegalArgumentException("Size must be greater than 1. And default size is 32 bit");
        }
        int size = 0;

        if( bitSize%32 == 0 ) {
            size = bitSize/32;
        } else {
            size = bitSize/32 + 1;
        }


        int arr[] = new int[size];

        String full = "";
        String myarr = "";
        
        try {
            BufferedReader reader = new BufferedReader(new FileReader("test2.txt")); 
                myarr = reader.readLine(); 
                reader.close();
        } catch (IOException e) {
            System.out.println("Don't panic the file just needs to be created this is the first time enter the index :))");
        }

      
        if(!(myarr.equals(""))){

            String trimmedString = myarr.substring(1, myarr.length() - 1);
            String[] numberStrings = trimmedString.split(", ");
            if(numberStrings.length>=size) {
                for (int i = 0; i < size; i++) {
                arr[i] = Integer.parseInt(numberStrings[i]);
            }
            }
             

        }
        

        int index = 0;
        System.out.println("If you want to add 1 then enter 1 if 0 then 0 : ");
        int setReset = scanner.nextInt();
        String exit = "";


        System.out.println("Plz enter vector Index if you want to go out enter \"exit\" : ");
            while (scanner.hasNext()) {
                if (scanner.hasNextInt()) {
                    System.out.println("Plz enter vector Index if you want to go out enter \"exit\" : ");
                    index = scanner.nextInt();
                
                } else {
                    exit = scanner.next();
                    if (exit.equalsIgnoreCase("exit")) {
                        break;
                    } 
                }

                if(setReset == 1) {
                    setVector(index,arr);
                } else {
                    reSetVector(index,arr);
                }
            }
        
        try {
            BufferedWriter writer = new BufferedWriter(new FileWriter("test2.txt")); 
            writer.write(Arrays.toString(arr));  
            writer.close(); 
        } catch (IOException e) {
            e.printStackTrace();
        }
       

        String end = String.format("_"+String.format("%32s", Integer.toBinaryString(arr[0])).replace(" ", "0")) ;
      
        
        for(int i = size-1; i>0;i--) {
            full += String.format("_"+String.format("%32s", Integer.toBinaryString(arr[i])).replace(" ", "0")) ;
        }

        full += end;


        try {
            BufferedWriter writer = new BufferedWriter(new FileWriter("test.txt")); 
            writer.write(full);  
            writer.close(); 
        } catch (IOException e) {
            e.printStackTrace();
        }

        try {
            BufferedReader reader = new BufferedReader(new FileReader("test.txt"));
           
        while((full = reader.readLine()) != null) {
            System.out.println(full);
            }
            reader.close();
        } catch (IOException e) {
             e.printStackTrace();
        }

   }

   public static void  setVector(int index,int arr[]) {

            if (index < 0) {
            throw new IndexOutOfBoundsException("Index out of bounds.");
            }   
            arr[index/32] |= (1 << index);
    }
    
    public static void  reSetVector(int index,int arr[]) {
            if (index < 0) {
            throw new IndexOutOfBoundsException("Index out of bounds.");
            }
            arr[index/32] &= ~(1 << index);       
    }
        
}
