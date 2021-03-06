/**
 * @(#) Moniteur.java
 */
package FFSSM;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

public class Moniteur extends Plongeur {

    public int numeroDiplome;

    public List<Embauche> myEmplois = new ArrayList<Embauche>();

    public Moniteur(int numeroDiplome, int niveau, String numeroINSEE, String nom, String prenom, String adresse, String telephone, LocalDate naissance, GroupeSanguin groupe) {
        super(niveau, numeroINSEE, nom, prenom, adresse, telephone, naissance, groupe);
        this.numeroDiplome = numeroDiplome;
    }

    /**
     * Si ce moniteur n'a pas d'embauche, ou si sa dernière embauche est
     * terminée, ce moniteur n'a pas d'employeur.
     *
     * @return l'employeur actuel de ce moniteur sous la forme d'un Optional
     */
    public Optional<Club> employeurActuel() {
        Club dernierEmployeur = null;
        if (myEmplois.isEmpty() || myEmplois.get(myEmplois.size() - 1).estTerminee()) {
            return Optional.empty();
        }
        return Optional.of(myEmplois.get(myEmplois.size() - 1).getEmployeur());
    }

    /**
     * Enregistrer une nouvelle embauche pour cet employeur
     *
     * @param employeur le club employeur
     * @param debutNouvelle la date de début de l'embauche
     */
    public void nouvelleEmbauche(Club employeur, LocalDate debutNouvelle) throws Exception {
        Embauche derniereEmbauche = myEmplois.get(myEmplois.size() - 1);
        if (debutNouvelle.isBefore(derniereEmbauche.getFin())) {
            throw new Exception("La date de la nouvelle embauche doit être après la date de fin de la dernière embauche");
        }
        Embauche e = new Embauche(debutNouvelle, this, employeur);
        myEmplois.add(e);
    }

    public List<Embauche> emplois() {
        return myEmplois;

    }

    public void terminerEmbauche(LocalDate fin) throws Exception {
        if (this.employeurActuel().isEmpty()) {

            throw new IllegalArgumentException(this.nom + " " + this.prenom + " n'est pas employé.");

        } else {
            this.myEmplois.get(myEmplois.size() - 1).terminer(fin);

        }
    }

}
