package kutyakfeladat.view;

import java.text.DecimalFormat;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collection;
import java.util.Collections;
import java.util.Comparator;
import java.util.List;
import kutyakfeladat.controller.FajltKiir;
import kutyakfeladat.controller.KutyNevStatisztikaListaLetrehozo;
import kutyakfeladat.controller.KutyaFajtakBeolvas;
import kutyakfeladat.controller.KutyakBeolvas;
import kutyakfeladat.controller.KutyanevekBeolvas;
import kutyakfeladat.model.KutyaFajtakModel;
import kutyakfeladat.model.KutyaNevStatisztikaModel;
import kutyakfeladat.model.KutyaNevekModel;
import kutyakfeladat.model.KutyakModel;

public class KutyakFeladatKonzolraKiir 
{
    // Deklaráljuk, és meghatározzuk a listákat, amelyek azok az adatszerkezetek, 
    // amiket a 2.,4.,5. feladatban kérnek tőlünk.
    private static ArrayList<KutyakModel> kutyak = KutyakBeolvas.kutyak("Kutyak.csv", 5);
    private static ArrayList<KutyaNevekModel> kutyaNevek = KutyanevekBeolvas.kutyak("KutyaNevek.csv", 2);
    private static ArrayList<KutyaFajtakModel> kutyaFaj = KutyaFajtakBeolvas.kutyaFajtak("KutyaFajtak.csv", 3);

    //A program main metódusa, ami elindul a program futtatásakor 
    // (Új projekt létrehozásakor automatikusan generálódik
    public static void main(String[] args) 
    {
        System.out.println("3. feladat:  Kutyanevek száma: "+ String.valueOf(osszesKutyaNev()));
        System.out.println("6. feladat:  Kutyák átlag életkora: "+ String.valueOf(atlagEletkor()));
        System.out.println(legidosebbKutya());
        januarTizedikenVizsgaltKutyak();
        legLeterheltebbNap();
        nevStatisztikaFajltLetrehoz();
    }
    
    //Összes kutyát meghatározó metódus (összes kutyanév)
    //  3. Feladat
    private static int osszesKutyaNev()
    {
        //Kutya nevek száma, 3. feladat, a -1 azért szükséges,
        //mert az első indexünk a listában a fejléc, nem egy valid adat!!!
        return kutyaNevek.size()-1;
    }
    
    
    //Átlagszámítás : összes egyed adata osztva az egyedek számával
    // Tehát összesen a kutyák életkora/kutyák számával
    //  6. Feladat
    private static String atlagEletkor()
    {

        //A tömbből ki kell vennünk a 0. elemet, mert az a fejléc
        kutyak.remove(0);

        //Az összes kutya számának meghatározása
        int osszeskutya = kutyak.size();

        //Ebben a változóban tároljuk a kutyák életkorát
        float eletkor = 0;

        //Bejárjuk a Kutyák listában a kutyák korát
        for (KutyakModel kutyakModel : kutyak) 
        {
            //növeljük az értékét az összes kutya korának
            eletkor += Float.valueOf(kutyakModel.getKutyaKora());
        }
        
        //Átlag számítása
        float atlagEletkor = eletkor/osszeskutya;
        //Kerekítés
        DecimalFormat df = new DecimalFormat("##.00");
        String kerekitettAtlagEletkor = df.format(atlagEletkor);
        
        //A metódus visszatérési értéke a megoldás maga! 
        return kerekitettAtlagEletkor;
        
    }
    
    
    //  7. Feladat kezdete
    //Legidősebb kutyának a korát meghatározza, 
    //ez alapján lekérjük az azonosítóit
    private static String legidosebbKutya()
    {
        kutyak.remove(0);

        int kor;
        int kutyaNevID = 0;
        int kutyaFajtaID = 0;
        int legidosebb = 0;
        for (KutyakModel kutyakModel : kutyak) 
        {
            kor = Integer.valueOf(kutyakModel.getKutyaKora());
            if(kor > legidosebb)
            {
                legidosebb = kor;
                if(kor == legidosebb)
                {
                    kutyaNevID = Integer.valueOf(kutyakModel.getKutyaNevID());
                    kutyaFajtaID = Integer.valueOf(kutyakModel.getKutyaFajtaID());
                }
            }
            
        }
        
        return legidosebbKutyatMegkeres(kutyaNevID, kutyaFajtaID);
    }
    
   
    //Legidősebb kutyának a nevét és a fajtáját is megkeresi
    private static String legidosebbKutyatMegkeres(int kutyaNevID, int kutyaFajtaID)
    {
        String kutyaNeve = "";
        kutyaNevek.remove(0);
        for (KutyaNevekModel kutyaNevekModel : kutyaNevek) {
            if(kutyaNevID == Integer.valueOf(kutyaNevekModel.getAzonosito()))
            {
                kutyaNeve = kutyaNevekModel.getKutyaNeve();
            }
        }
        
        String kutyaFajtaja = "";
        kutyaFaj.remove(0);
        for (KutyaFajtakModel kutyaFajtakModel : kutyaFaj) 
        {
            if(Integer.valueOf(kutyaFajtakModel.getAzonosito()) == kutyaFajtaID)
            {
                kutyaFajtaja = kutyaFajtakModel.getMagyarNev();
            }
        }
        
        return "7. feladat: Legidősebb kutya neve és fajtája: "+kutyaNeve+", "+kutyaFajtaja;
    }
    //  7. Feladat vége
    
    
    // 8. Feladat 2018.01.10 napon vizsgált kutyák
    private static void januarTizedikenVizsgaltKutyak()
    {
        String vizsgaltDatum = "2018.01.10";
        String start = "8. feladat : Január 10.-én vizsgált kutya fajták \r";
        int kutyakSzama;
        
        System.out.println(start);
        
        String kutyaFajtaID = "";
        for (KutyakModel kutyakModel : kutyak) 
        {
            kutyakSzama = 0;
            if(kutyakModel.getVizsgalatDatuma().equals(vizsgaltDatum))
            { 
                kutyaFajtaID = kutyakModel.getKutyaFajtaID();
                                
                for (KutyaFajtakModel kutyaFajtakModel : kutyaFaj)
                {
                    if(kutyaFajtakModel.getAzonosito().equals(kutyaFajtaID))
                    {
                        kutyakSzama++;
                        String kutyaNeve = kutyaFajtakModel.getMagyarNev();
                        System.out.println(kutyaNeve+" "+kutyakSzama+" kutya ");
                    }
                }
            }
        }
    }
    
    //9. Feladat melyik nap volt a legleterheltebb nap a rendelőben
    private static void legLeterheltebbNap()
    {
        int maxKutyak = 0;
        String datum = "";
        for (KutyakModel kutyakModel : kutyak) 
        {
            String vizsgaltDatum = kutyakModel.getVizsgalatDatuma();
            int szamlalo = 0;
            
            for (int i = 0; i < kutyak.size(); i++) 
            {
                if(kutyak.get(i).getVizsgalatDatuma().equals(vizsgaltDatum))
                {
                    szamlalo++;
                    if(szamlalo > maxKutyak)
                    {
                        maxKutyak = szamlalo;
                        datum = vizsgaltDatum;
                    }
                }
            }
        }
        
        System.out.println("9. feladat: Legjobban leterhelt nap: "+datum + " : "+maxKutyak+" kutya");
    }
    
    //10. Feladat Névstatisztika.txt létrehozás
    private static void nevStatisztikaFajltLetrehoz()
    {
        ArrayList<KutyaNevStatisztikaModel> statisztikaNevek 
                = KutyNevStatisztikaListaLetrehozo.kutyaNevekStatisztika(kutyaNevek,kutyak);
        
        ArrayList<String> kiirandoEredmeny = new ArrayList<>();
        
        int hossz = statisztikaNevek.size();
        System.out.println(" hossz "+hossz);

        Collections.sort(statisztikaNevek,Collections.reverseOrder(Comparator.comparing(KutyaNevStatisztikaModel::getDarabSzam)));

        for (KutyaNevStatisztikaModel kutyaNevStatisztikaModel : statisztikaNevek) 
        {
            
            String nev = kutyaNevStatisztikaModel.getKutyaNeve();
            String db = kutyaNevStatisztikaModel.getDarabSzam();

            String egySorbaIr =  nev+";"+db;
            kiirandoEredmeny.add(egySorbaIr);
            FajltKiir.FajltKiir(kiirandoEredmeny, "Névstatisztika.txt");

        }
        

    }
}