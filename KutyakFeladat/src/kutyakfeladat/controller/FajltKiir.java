package kutyakfeladat.controller;

import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.RandomAccessFile;
import java.util.ArrayList;


public class FajltKiir {
    
    String sor;
    String [] szeletek;
    
    public static void FajltKiir(ArrayList<String> sor,String fajlNeve)
    {
        RandomAccessFile fajlIro;
        try 
        {
            fajlIro = new RandomAccessFile(fajlNeve, "rw");
            for (String egySor : sor) 
            {
                fajlIro.writeBytes(egySor+"\r"+"\n");
            }
        }
        catch (FileNotFoundException ex) 
        {
            System.out.println(ex.getMessage());
            
        }
        catch (IOException ex) 
        {
            System.out.println(ex.getMessage());
        }
    }
}
