/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;
import FFSSM.Club;
import FFSSM.Embauche;
import FFSSM.Licence;
import FFSSM.Moniteur;
import FFSSM.Plongee;
import FFSSM.Plongeur;
import FFSSM.Site;
import java.time.LocalDate;
import java.time.Month;

/**
 *
 * @author Juju Vilas
 */
public class PlongeeTest {

    private Plongeur pr1, pr2;
    private Moniteur m1;
    private Site Méditerranée;
    private Plongee p1, p2;
    private Club c1;
    private Embauche e1;
    private Licence l1, l2;

    public PlongeeTest() {
    }

    @BeforeEach
    public void setUp() {
        l1 = new Licence(pr1, "463", LocalDate.of(2020, Month.JULY, 10), 3, c1);
        l2 = new Licence(pr2, "624", LocalDate.of(2017, Month.FEBRUARY, 03), 1, c1);
        pr1 = new Plongeur(3, "CD5454", "Aoubiza", "Imane", "rue des champs", "tel45652", LocalDate.of(2000, Month.JUNE, 19));
        pr2 = new Plongeur(1, "GB5494", "Vilas", "Julia", "allée des oliviers", "tel464974", LocalDate.of(2000, Month.JULY, 17));
        m1 = new Moniteur(352, 5, "VZ274", "Sutarik", "Agathe", "rue Simone de Beauvoir", "tel275384", LocalDate.of(2000, Month.APRIL, 28));
        p1 = new Plongee(Méditerranée, m1, LocalDate.of(2020, Month.AUGUST, 14), 5, 3);
        p2 = new Plongee(Méditerranée, m1, LocalDate.of(2020, Month.SEPTEMBER, 9), 4, 3);
        c1 = new Club(m1, "Club de plongée de Castres", "tel952493");
        e1 = new Embauche(LocalDate.of(2016, Month.APRIL, 19), m1, c1);
    }

    // Méthodes de Club : 
    @Test
    public void testPlongeesNonConformes() throws Exception {
        pr1.ajouteLicence("463", LocalDate.of(2020, Month.JULY, 10));
        pr2.ajouteLicence("624", LocalDate.of(2017, Month.FEBRUARY, 03));
        // La plongée p1 comporte le plongeur pr2 qui a une licence non valide
        p1.ajouteParticipant(pr1);
        p1.ajouteParticipant(pr2);
        // La plongée p2 ne comporte que des plongeurs dont la licence est valide.
        p2.ajouteParticipant(pr1);
        c1.organisePlongee(p1);
        c1.organisePlongee(p2);
        assertEquals(p1,c1.plongeesNonConformes());
    }

    @Test
    public void testOrganisePlongee() throws Exception{
        // On ajoute la plongée p1 au club c1
        c1.organisePlongee(p1);
        try {
            c1.organisePlongee(p1);
            // Si on arrive ici, il n'y a pas eu d'exception, échec
            fail("La plongée est déjà organisée");
        } catch (IllegalArgumentException e) {
            // Si on arrive ici, il y a eu une exception, c'est ce qui est attendu
        }
    }

    // Méthodes d'Embauche : 
    @Test
    public void testTerminer() throws Exception {
        LocalDate d = LocalDate.parse("2016-05-30");
        e1.terminer(d);
        assertEquals(d, e1.getFin(), "La date de fin n'est pas correcte");
    }

    @Test
    public void testEstTerminee() throws Exception {
        LocalDate d = LocalDate.parse("2016-05-30");
        e1.terminer(d);
        assertTrue(e1.estTerminee(), "L'embauche est censée être terminée");
    }

    @Test
    public void testSetDateFin() throws Exception {
        try {
            e1.setFin(LocalDate.of(2013, Month.MARCH, 1));
            // Si on arrive ici, il n'y a pas eu d'exception, échec
            fail("La date doit être refusée");
        } catch (IllegalArgumentException e) {
            // Si on arrive ici, il y a eu une exception, c'est ce qui est attendu
        }
    }

    // Méthodes de Licence : 
    @Test
    public void testEstValide() {
        LocalDate d = LocalDate.parse("2020-05-30");
        assertTrue(l1.estValide(d));
        assertFalse(l2.estValide(d));
    }

    // Méthodes de Moniteur : 
    @Test
    public void testEmployeurActuel() {
        // Je ne sais pas tester les optional : je ne suis pas sûre d'avoir compris comment cela fonctionnait
    }

    @Test
    public void testNouvelleEmbauche() throws Exception {
        // Le moniteur a été embauché le 19 avril 2016 dans le club c1 (voir e1)
        // Son contrat se termine le 21 mai 2018
        e1.terminer(LocalDate.of(2018, Month.MAY, 21));
        try {
            m1.nouvelleEmbauche(c1, LocalDate.of(2017, Month.MARCH, 21));
            // Si on arrive ici, il n'y a pas eu d'exception, échec
            fail("La date doit être refusée");
        } catch (IllegalArgumentException e) {
            // Si on arrive ici, il y a eu une exception, c'est ce qui est attendu
        }
    }

    // Méthodes de Plongee : 
    @Test
    public void testAjouteParticipant() throws Exception {
        // On ajoute le plongeur pr1 à la plongée p1
        p1.ajouteParticipant(pr1);
        try {
            p1.ajouteParticipant(pr1);
            // Si on arrive ici, il n'y a pas eu d'exception, échec
            fail("Le plongeur participe déjà");
        } catch (IllegalArgumentException e) {
            // Si on arrive ici, il y a eu une exception, c'est ce qui est attendu
        }
    }

    @Test
    public void testEstConforme() {
        // Je ne sais pas comment tester cette méthode
    }

    // Méthodes de Plongeur : 
    @Test
    public void testAjouteLicence() throws Exception {
        pr1.ajouteLicence("965", LocalDate.of(2017, Month.DECEMBER, 4));
        try {
            pr1.ajouteLicence("524", LocalDate.of(2017, Month.SEPTEMBER, 25));
            // Si on arrive ici, il n'y a pas eu d'exception, échec
            fail("La date doit être refusée");
        } catch (IllegalArgumentException e) {
            // Si on arrive ici, il y a eu une exception, c'est ce qui est attendu
        }

    }
}
