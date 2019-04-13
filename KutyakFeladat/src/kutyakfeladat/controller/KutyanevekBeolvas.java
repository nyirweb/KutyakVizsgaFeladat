package kutyakfeladat.controller;

import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.RandomAccessFile;
import java.util.ArrayList;
import kutyakfeladat.model.KutyaNevekModel;


public class KutyanevekBeolvas {
        
    public static ArrayList<KutyaNevekModel> kutyak(String fajlNeve,int szeletekSzama)
    {
        RandomAccessFile raf;

        ArrayList<KutyaNevekModel> kutyak = new ArrayList<>();
        KutyaNevekModel egyKutya;
        
        String sor;
        String[] szeletek = new String[szeletekSzama];

        try 
        {
            raf = new RandomAccessFile(fajlNeve, "r");            
            sor = raf.readLine();
            
            while(sor != null)
            {
                String utf = new String(sor.getBytes("ISO-8859-1"));

                szeletek = utf.split(";");
                egyKutya = new KutyaNevekModel();
                egyKutya.setAzonosito(szeletek[0]);
                egyKutya.setKutyaNeve(szeletek[1]);
                
                kutyak.add(egyKutya);
                sor = raf.readLine();
            }
            raf.close();
        } 
        catch (FileNotFoundException ex) 
        {
        }
        catch (IOException ex) 
        {
        }
        return kutyak;
    }
}
