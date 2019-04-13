package kutyakfeladat.controller;

import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.RandomAccessFile;
import java.util.ArrayList;
import java.util.logging.Level;
import java.util.logging.Logger;


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
            
        }
        catch (IOException ex) 
        {
            
        }
    }
    
    
    
    /*
    public void FajlBeolvasas(String fajlNeve, int elsoIndex, int szeletekSzama)
    {
        try 
        {
            raf = new RandomAccessFile(fajlNeve, "r");
            
            raf.seek(elsoIndex);
            sor = raf.readLine();
            szeletek = new String[szeletekSzama];
            
        } 
        catch (FileNotFoundException ex) {
            Logger.getLogger(FajltKiir.class.getName()).log(Level.SEVERE, null, ex);
        } catch (IOException ex) {
            Logger.getLogger(FajltKiir.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
    */
}
