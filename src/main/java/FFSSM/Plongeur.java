package FFSSM;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;



public class Plongeur extends Personne{
    
    private int niveau;
    
    List<Licence> myLicences = new ArrayList<>();

    public Plongeur(int niveau, String numeroINSEE, String nom, String prenom, String adresse, String telephone, LocalDate naissance) {
        super(numeroINSEE, nom, prenom, adresse, telephone, naissance);
        this.niveau = niveau;
    }
    
    public void ajouteLicence(String numero, LocalDate delivrance) throws Exception{
        if(this.derniereLicence().getDelivrance().isBefore(delivrance)){
            throw new Exception("La date de délivrance de la nouvelle licence est avant la date de délivrance de la dernière");
        }
        if(this.derniereLicence().estValide(delivrance)){
            throw new Exception("La derniere licence est valide");
        }
        // On utilise un autre constructeur créé dans Licence qui prend en paramètre que le numéro et la date de délivrance
        Licence l = new Licence(numero,delivrance);
        myLicences.add(l);
    }
    
    public Licence derniereLicence(){
            Licence derniereLicence = myLicences.get(myLicences.size()-1);
            return derniereLicence;
        }
        
    }
