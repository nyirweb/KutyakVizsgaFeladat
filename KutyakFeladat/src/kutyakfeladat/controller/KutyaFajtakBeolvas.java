package kutyakfeladat.controller;

import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.RandomAccessFile;
import java.util.ArrayList;
import kutyakfeladat.model.KutyaFajtakModel;


public class KutyaFajtakBeolvas 
{
    public static ArrayList<KutyaFajtakModel> kutyaFajtak(String fajlNeve, int oszlopokSzama)
    {
        ArrayList<KutyaFajtakModel> fajtak = new ArrayList<>();
        KutyaFajtakModel egyFaj;
        
        RandomAccessFile randomAF;
        
        String[] szeletek = new String[oszlopokSzama];
        String sor; 
        
        try 
        {
            randomAF = new RandomAccessFile(fajlNeve, "r");
            sor = randomAF.readLine();

            while(sor != null)
            {

                String utf = new String(sor.getBytes("ISO-8859-1"));
                
                szeletek = utf.split(";");

                if(szeletek.length >= 3)
                {
                    String id = szeletek[0];
                    String hname = szeletek[1];
                    String oname = szeletek[2];
                    
                    egyFaj = new KutyaFajtakModel();
                    egyFaj.setAzonosito(id);
                    egyFaj.setMagyarNev(hname);
                    egyFaj.setEredetiNev(oname);
                
                    fajtak.add(egyFaj);                    
                    sor = randomAF.readLine();
                }else
                {
                    sor = randomAF.readLine();
                }

            }
            
        }
        catch (FileNotFoundException ex)
        {
        } catch (IOException ex) {
        }
        return fajtak;
    }
    
}
