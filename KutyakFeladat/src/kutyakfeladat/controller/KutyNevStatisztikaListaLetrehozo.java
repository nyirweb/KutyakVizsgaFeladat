package kutyakfeladat.controller;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.Iterator;
import kutyakfeladat.model.KutyaNevStatisztikaModel;
import kutyakfeladat.model.KutyaNevekModel;
import kutyakfeladat.model.KutyakModel;
import sun.management.counter.Counter;

public class KutyNevStatisztikaListaLetrehozo 
{
    
    public static ArrayList<KutyaNevStatisztikaModel> kutyaNevekStatisztika
        (ArrayList<KutyaNevekModel> kutyaNevek, ArrayList<KutyakModel> kutyak)
    {
        ArrayList<KutyaNevStatisztikaModel> kutyaNevekStatisztikaLista = new ArrayList<>();
        ArrayList<KutyaNevStatisztikaModel> ks = new ArrayList<>();
        KutyaNevStatisztikaModel egyKutyNevElofordulasa = new KutyaNevStatisztikaModel();
        
        String nev = "";
        String max = "";


        HashSet<String> mindenEredmeny = new HashSet<>();

        for (KutyakModel kutyakModel : kutyak) 
        {
            int szamlalo = 0;   
            String vizsgaltNevID = kutyakModel.getKutyaNevID();
            
            egyKutyNevElofordulasa = modelElem(szamlalo, vizsgaltNevID,kutyaNevek,kutyak);
            
            nev = egyKutyNevElofordulasa.getKutyaNeve();
            max = egyKutyNevElofordulasa.getDarabSzam();

            if(! mindenEredmeny.contains(nev))
            {
                kutyaNevekStatisztikaLista.add(egyKutyNevElofordulasa);
                mindenEredmeny.add(nev);
            }
            
        }
        
        
        
        return kutyaNevekStatisztikaLista;
    }    

    private static KutyaNevStatisztikaModel modelElem(int szamlalo, String vizsgaltNevID, 
            ArrayList<KutyaNevekModel> kutyaNevek, ArrayList<KutyakModel> kutyak)
    {
        KutyaNevStatisztikaModel egyKutyNevElofordulasa = new KutyaNevStatisztikaModel();
        
        int maxKutyak = 0;
        String nevID = "";
        int max = 0;
        
        for (int i = 0; i < kutyak.size(); i++) 
        {
        
            if(kutyak.get(i).getKutyaNevID().equals(vizsgaltNevID))
            {
                szamlalo++;
                if(szamlalo > maxKutyak)
                {                
                    maxKutyak = szamlalo;
                    max = Math.max(1, maxKutyak);
                        
                    nevID = vizsgaltNevID;
                    maxKutyak = 0;                 
                }
            }
        }
        

        String nev = kutyaNevetMeghataroz(kutyaNevek,nevID);
        egyKutyNevElofordulasa.setKutyaNeve(nev);
        egyKutyNevElofordulasa.setDarabSzam(String.valueOf(max));

        return egyKutyNevElofordulasa;
    }

        
        /*private static KutyaNevStatisztikaModel modelElem
        (ArrayList<KutyaNevekModel> kutyaNevek, ArrayList<KutyakModel> kutyak)
    {
        KutyaNevStatisztikaModel egyKutyNevElofordulasa = new KutyaNevStatisztikaModel();
        
        int maxKutyak = 0;
        String nevID = "";
        int max = 0;
        int szamlalo = 0;
        
        for (KutyakModel kutyakModel : kutyak) 
        {
            String vizsgaltNevID = kutyakModel.getKutyaNevID();
            szamlalo = 0;
            
            for (int i = 0; i < kutyak.size(); i++) 
            {
                if(kutyak.get(i).getKutyaNevID().equals(vizsgaltNevID))
                {
                    szamlalo++;
                    if(szamlalo > maxKutyak)
                    {
                        maxKutyak = szamlalo;
                        max = Math.max(1, maxKutyak);
                        
                        nevID = vizsgaltNevID;
                        maxKutyak = 0;

                    }
                }
            }
            String nev = kutyaNevetMeghataroz(kutyaNevek, nevID);
            egyKutyNevElofordulasa.setKutyaNeve(nev);
            egyKutyNevElofordulasa.setDarabSzam(String.valueOf(max));
        }


        return egyKutyNevElofordulasa;
    }
    */    
    private static String kutyaNevetMeghataroz(ArrayList<KutyaNevekModel> kutyaNevek,String nevID)
    {
        String nev = "";
        
        for (KutyaNevekModel kutyaNevekModel : kutyaNevek) 
        {
            if(kutyaNevekModel.getAzonosito().equals(nevID))
            {
                nev = kutyaNevekModel.getKutyaNeve();
            }
        }
        
        return nev;
    }
    
}
