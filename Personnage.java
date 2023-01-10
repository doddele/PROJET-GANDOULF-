import javax.swing.*;
import java.io.FileInputStream;
import java.io.IOException;
import java.util.Objects;
import java.util.Scanner;

public class Personnage {
    protected String nom;
    protected String classe;
    protected int level;
    protected int XP;
    protected int defense;
    protected int HPmax;
    protected int HP;
    protected int seuilXP;
    protected int esquive;
    protected int vitesse;
    protected String arme;
    protected String armure;
    protected int or;
    protected int nbPotions;

    public Personnage(String nom) {
        this.nom = nom;
        this.level = 0;
        this.XP = 0;
        this.defense = 0;
        this.HP = 100;
        this.HPmax = 100;
        this.seuilXP = 100;
        this.esquive = 1;
        this.vitesse = 10;
        this.arme = "vide";
        this.armure = "vide";
        this.or = 50;
        this.nbPotions = 0;
    }

    public void setNom(String nom) {

        this.nom = nom;
    }

    public String getNom() {

        System.out.println(this.nom);
        return this.nom;
    }
    public void setOr(int or){

        this.or = or;
    }
    public int getOr() {

        return this.or;
    }
    public void setNbPotions(int x) {

        this.nbPotions = x;
    }
    public String setArme(String arme){

        this.arme = arme;
        return arme;
    }
    public void rename(String newNom) {
        this.nom = newNom;
    }
    public String getStat(){
        return this.nom;
    };

    public void creerPerso() {

        JFrame f = new JFrame();
        String [] persoSplit = {};
        boolean count = true;

        while(count == true) {
            Boolean nomUtilise = false;
            nom = JOptionPane.showInputDialog(f,
                    "Veuillez rentrer le nom de votre personnage: ",
                    "Création de personnage", -1);
            try {

                // Fichier d'entrée.
                FileInputStream file = new FileInputStream("/home/jules/Desktop/PROJET GANDOULF/Ressources/personnages.txt");
                Scanner scanner = new Scanner(file);

                // Renvoie true s'il y a une autre ligne à lire.
                while(scanner.hasNextLine()){

                    // Split les données de la ligne lue.
                    var a = scanner.nextLine();
                    persoSplit = a.split(";");

                    // Return "nomUtilise = true" si le nom saisi correspond à un nom enregistré.
                    if(Objects.equals(persoSplit[0], nom)) {
                        nomUtilise=true;
                    }
                }
                scanner.close();
            }
            catch(IOException e) {
                e.printStackTrace();
            }

            // Affiche un message d'erreur si le nom saisi existe déjà.
            if(nomUtilise == true) {
                JOptionPane.showMessageDialog(f, "Un personnage de ce nom existe déjà!");
            }

            // Crée un nouveau personnage.
            if(nomUtilise==false) {

                // Ferme la boucle.
                count=false;
            }
        }
    }
    public void save() {}
    public void death() {
        if (this.HP <= 0) {
            JFrame g;
            g = new JFrame();
            //System.out.println(this.nom + " est mort!");
            JOptionPane.showMessageDialog(g, this.nom + " est mort.", this.nom + " : " + this.HP + "PV", -1);
        }
    }
}