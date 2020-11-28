/**
 * @(#) Plongee.java
 */
package FFSSM;

import java.time.LocalDate;
import java.util.HashSet;
import java.util.Set;

public class Plongee {

    public Site lieu;

    public Moniteur chefDePalanquee;

    public LocalDate date;

    public int profondeur;

    public int duree;

    Set<Plongeur> myPlongeurs = new HashSet<Plongeur>();

    public Plongee(Site lieu, Moniteur chefDePalanquee, LocalDate date, int profondeur, int duree) {
        this.lieu = lieu;
        this.chefDePalanquee = chefDePalanquee;
        this.date = date;
        this.profondeur = profondeur;
        this.duree = duree;
    }

    public void ajouteParticipant(Plongeur participant) throws Exception{
        if(myPlongeurs.contains(participant)){
            throw new Exception("Le plongeur est déjà dans la liste");
            // Comme c'est une HashSet qui évite les doublons, je ne sais pas si cela est vraiment nécessaire
        }
        myPlongeurs.add(participant);
    }

    public LocalDate getDate() {
        return date;
    }

    /**
     * Détermine si la plongée est conforme. Une plongée est conforme si tous
     * les plongeurs de la palanquée ont une licence valide à la date de la
     * plongée
     *
     * @return vrai si la plongée est conforme
     */
    
    public boolean estConforme() {
        for(Plongeur p : myPlongeurs){
            if(p.derniereLicence().estValide(date)){
            }else{
                return false;
            }
        }return true;
    }

    
    
    
    
}

