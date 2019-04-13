package kutyakfeladat.controller;

import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.RandomAccessFile;
import java.util.ArrayList;
import java.util.logging.Level;
import java.util.logging.Logger;
import kutyakfeladat.model.KutyakModel;


public class KutyakBeolvas 
{

    public static ArrayList<KutyakModel> kutyak(String fajlNev,int oszlopok)
    {
        ArrayList<KutyakModel> kutyakLista = new ArrayList<>();
        KutyakModel egyKutya;
        
        RandomAccessFile raf;
        String sor = null;
        
        String[] szeletek = new String[oszlopok];
        
        try 
        {
            raf = new RandomAccessFile(fajlNev, "r");
            sor = raf.readLine();
            
            while(sor != null)
            {
                String utf = new String(sor.getBytes("ISO-8859-1"));
                szeletek = utf.split(";");
                
                egyKutya = new KutyakModel();
                
                egyKutya.setVizsgalatID(szeletek[0]);
                egyKutya.setKutyaFajtaID(szeletek[1]);
                egyKutya.setKutyaNevID(szeletek[2]);
                egyKutya.setKutyaKora(szeletek[3]);
                egyKutya.setVizsgalatDatuma(szeletek[4]);
                
                kutyakLista.add(egyKutya);
                
                sor=raf.readLine();
            }
        }
        catch (FileNotFoundException ex) 
        {
            ex.getMessage();
        } catch (IOException ex) 
        {
            ex.getMessage();
        }
        
        return kutyakLista;
    }
    
}
