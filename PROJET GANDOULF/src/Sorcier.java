import javax.swing.*;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileWriter;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.StandardCopyOption;
import java.util.Objects;
import java.util.Scanner;

public class Sorcier extends Personnage {
    private double malusEquipement;
    private String sorts;

    public Sorcier(String nom) {
        super(nom);
        this.attaque = 5;
        this.mana = 50;
        this.manaMax = 50;
        this.malusEquipement = 10;
        this.sorts = "/////////";   // String qui va contenir la liste des sorts du sorcier. (ex: Boule de feu/Eclair/.../)
        this.classe = "Sorcier";
    }

    public Sorcier(String nom, int level, int XP, int HP, int HPmax, int mana, int manaMax, int attaque, int defense, double esquive,
                   int vitesse, String arme, String armure, int or, int nbPotions, String sorts) {
        super(nom);
        this.level = level;
        this.XP = XP;
        this.HPmax = HPmax;
        this.HP = HP;
        this.manaMax = manaMax;
        this.mana = mana;
        this.attaque = attaque;
        this.defense = defense;
        this.esquive = esquive;
        this.vitesse = vitesse;
        this.arme = arme;
        this.armure = armure;
        this.or = or;
        this.nbPotions = nbPotions;
        this.classe = "Sorcier";
        this.sorts = sorts;
        this.malusEquipement = 10;
    }

    @Override
    public void save() {

        try {
            // Ecrit les statistiques du personnage dans une nouvelle ligne du fichier
            File fichierPerso = new File("/home/jules/Desktop/PROJET GANDOULF/Ressources/personnages.txt");
            FileWriter myWriter = new FileWriter(
                    "/home/jules/Desktop/PROJET GANDOULF/Ressources/personnages.txt", true);
            System.out.println(nom);
            myWriter.write(this.nom + ";" + this.classe + ";" + this.level + ";" + this.XP + ";" + this.HPmax
                    + ";" + this.HP + ";" + this.manaMax + ";" + this.mana + ";" + this.attaque + ";" + this.defense
                    + ";" + this.esquive + ";" + this.vitesse + ";" + this.arme + ";" + this.armure + ";" + this.or
                    + ";" + this.nbPotions + ";" + this.sorts + "\n");
            myWriter.close();
        } catch (IOException e) {
            e.printStackTrace();
        }
/*
        try {
            // Fichier d'entrée.
            File inputFile = new File("/home/jules/Desktop/PROJET GANDOULF/Ressources/personnages.txt");

            // Fichier temporaire.
            File tempFilePerso = new File("/home/jules/Desktop/PROJET GANDOULF/Ressources/tempFilePerso.txt");

            // Copie du fichier personnages vers le fichier temporaire.
            Files.move(inputFile.toPath(), tempFilePerso.toPath(), StandardCopyOption.REPLACE_EXISTING);
        } catch (IOException ex) {
            ex.printStackTrace();
        }

 */


    }

    @Override
    public String getStat() {
        return (this.nom + ";" + this.classe + ";" + this.level + ";" + this.XP + ";" + this.HPmax
                + ";" + this.HP + ";" + this.manaMax + ";" + this.mana + ";" + this.attaque + ";" + this.defense
                + ";" + this.esquive + ";" + this.vitesse + ";" + this.arme + ";" + this.armure + ";" + this.or
                + ";" + this.nbPotions + ";" + this.sorts + "\n");
    }

    @Override
    public void gainNiveau() {
        JOptionPane.showMessageDialog(null, this.nom + " passe du niveau " + this.level + " au niveau " + (this.level + 1) + " !\n" +
                "\nHP: " + this.HPmax + " -> " + (this.HPmax + 20) +
                "\nVIT: " + this.vitesse + " -> " + (this.vitesse + 5) +
                "\nMANA: " + this.manaMax + " -> " + (this.manaMax + 25));
        this.level += 1;
        this.HPmax += 20;
        this.HP = this.HPmax;
        this.vitesse += 5;
        this.manaMax += 25;
        this.mana = this.manaMax;
        if(this.level == 5){
            JOptionPane.showMessageDialog(null, this.nom + " a appris le sort Eclair !");
        }
        else if(this.level == 10){
            JOptionPane.showMessageDialog(null, this.nom + " a appris le sort Glace !");
        }
        else if(this.level == 20){
            JOptionPane.showMessageDialog(null, this.nom + " a appris le sort Calcination !");
        }
        else if(this.level == 30){
            JOptionPane.showMessageDialog(null, this.nom + " a appris le sort Foudre !");
        }
        else if(this.level == 40){
            JOptionPane.showMessageDialog(null, this.nom + " a appris le sort Rayon de givre !");
        }
        else if(this.level == 50){
            JOptionPane.showMessageDialog(null, this.nom + " a appris le sort Tempête élémentaire !");
        }
    }
/*
    @Override
    public void lancerSort(Personnage joueur, Monstre monstre) {
        if (joueur.level < 2) {
            String[] sorts = {"Boule de feu"};
            String getSort = (String) JOptionPane.showInputDialog(null,
                    "Quel sort voulez vous lancer ?",
                    "Combat",
                    JOptionPane.QUESTION_MESSAGE,
                    null,
                    sorts,
                    sorts[0]);
            switch (getSort) {
                case "Boule de feu":
                    if (joueur.mana < 10) {
                        JOptionPane.showMessageDialog(null, "Vous n'avez pas assez de mana pour lancer ce sort.");
                    } else if (joueur.mana > 10) {
                        joueur.mana -= 10;
                        monstre.PVMonstre -= 15;
                        JOptionPane.showMessageDialog(null, "Vous avez infligé 15 points de dégats au " + monstre.nomMonstre);
                    }
                    break;
            }
        } else if (joueur.level < 5 && joueur.level > 2) {
            String[] sorts = {"Boule de feu", "Eclair"};
            String getSort = (String) JOptionPane.showInputDialog(null,
                    "Quel sort voulez vous lancer ?",
                    "Combat",
                    JOptionPane.QUESTION_MESSAGE,
                    null,
                    sorts,
                    sorts[1]);
            switch (getSort) {
                case "Boule de feu":
                    if (joueur.mana < 10) {
                        JOptionPane.showMessageDialog(null, "Vous n'avez pas assez de mana pour lancer ce sort.");
                    } else if (joueur.mana > 10) {
                        joueur.mana -= 10;
                        monstre.PVMonstre -= 15;
                        JOptionPane.showMessageDialog(null, "Vous avez infligé 15 points de dégats au " + monstre.nomMonstre);
                    }
                    break;
                case "Eclair":
                    if (joueur.mana < 20) {
                        JOptionPane.showMessageDialog(null, "Vous n'avez pas assez de mana pour lancer ce sort.");
                    } else if (joueur.mana > 20) {
                        joueur.mana -= 20;
                        monstre.PVMonstre -= 30;
                        JOptionPane.showMessageDialog(null, "Vous avez infligé 30 points de dégats au " + monstre.nomMonstre);
                    }
                    break;
            }
        } else if (joueur.level < 10 && joueur.level > 5) {
            String[] sorts = {"Boule de feu", "Eclair", "Glace"};
            String getSort = (String) JOptionPane.showInputDialog(null,
                    "Quel sort voulez vous lancer ?",
                    "Combat",
                    JOptionPane.QUESTION_MESSAGE,
                    null,
                    sorts,
                    sorts[2]);
            switch (getSort) {
                case "Boule de feu":
                    if (joueur.mana < 10) {
                        JOptionPane.showMessageDialog(null, "Vous n'avez pas assez de mana pour lancer ce sort.");
                    } else if (joueur.mana > 10) {
                        joueur.mana -= 10;
                        monstre.PVMonstre -= 15;
                        JOptionPane.showMessageDialog(null, "Vous avez infligé 15 points de dégats au " + monstre.nomMonstre);
                        monstre.attaquer(joueur, armure);
                    }
                    break;
                case "Eclair":
                    if (joueur.mana < 20) {
                        JOptionPane.showMessageDialog(null, "Vous n'avez pas assez de mana pour lancer ce sort.");
                    } else if (joueur.mana > 20) {
                        joueur.mana -= 20;
                        monstre.PVMonstre -= 30;
                        JOptionPane.showMessageDialog(null, "Vous avez infligé 30 points de dégats au " + monstre.nomMonstre);
                        monstre.attaquer(joueur, armure);
                    }
                    break;
                case "Glace":
                    if (joueur.mana < 50) {
                        JOptionPane.showMessageDialog(null, "Vous n'avez pas assez de mana pour lancer ce sort.");
                    } else if (joueur.mana > 50) {
                        joueur.mana -= 40;
                        monstre.PVMonstre -= 60;
                        JOptionPane.showMessageDialog(null, "Vous avez infligé 60 points de dégats au " + monstre.nomMonstre);
                        monstre.attaquer(joueur, armure);
                    }
                    break;
            }
        } else if (joueur.level < 10 && joueur.level > 5) {
                String[] sorts = {"Boule de feu", "Eclair", "Glace"};
                String getSort = (String) JOptionPane.showInputDialog(null,
                        "Quel sort voulez vous lancer ?",
                        "Combat",
                        JOptionPane.QUESTION_MESSAGE,
                        null,
                        sorts,
                        sorts[2]);
                switch (getSort) {
                    case "Boule de feu":
                        if (joueur.mana < 10) {
                            JOptionPane.showMessageDialog(null, "Vous n'avez pas assez de mana pour lancer ce sort.");
                        } else if (joueur.mana > 10) {
                            joueur.mana -= 10;
                            monstre.PVMonstre -= 15;
                            JOptionPane.showMessageDialog(null, "Vous avez infligé 15 points de dégats au " + monstre.nomMonstre);
                            monstre.attaquer(Personnage joueur, Armure armure);
                        }
                        break;
                    case "Eclair":
                        if (joueur.mana < 20) {
                            JOptionPane.showMessageDialog(null, "Vous n'avez pas assez de mana pour lancer ce sort.");
                        } else if (joueur.mana > 20) {
                            joueur.mana -= 20;
                            monstre.PVMonstre -= 30;
                            JOptionPane.showMessageDialog(null, "Vous avez infligé 30 points de dégats au " + monstre.nomMonstre);
                            monstre.attaquer(Personnage joueur, Armure armure);
                        }
                        break;
                    case "Glace":
                        if (joueur.mana < 50) {
                            JOptionPane.showMessageDialog(null, "Vous n'avez pas assez de mana pour lancer ce sort.");
                        } else if (joueur.mana > 50) {
                            joueur.mana -= 40;
                            monstre.PVMonstre -= 60;
                            JOptionPane.showMessageDialog(null, "Vous avez infligé 60 points de dégats au " + monstre.nomMonstre);
                            monstre.attaquer(Personnage joueur, Armure armure);
                        }
                        break;
                }
        } else if (joueur.level < 20 && joueur.level > 10) {
            String[] sorts = {"Boule de feu", "Eclair", "Glace", ""};
            String getSort = (String) JOptionPane.showInputDialog(null,
                    "Quel sort voulez vous lancer ?",
                    "Combat",
                    JOptionPane.QUESTION_MESSAGE,
                    null,
                    sorts,
                    sorts[2]);
            switch (getSort) {
                case "Boule de feu":
                    if (joueur.mana < 10) {
                        JOptionPane.showMessageDialog(null, "Vous n'avez pas assez de mana pour lancer ce sort.");
                    } else if (joueur.mana > 10) {
                        joueur.mana -= 10;
                        monstre.PVMonstre -= 15;
                        JOptionPane.showMessageDialog(null, "Vous avez infligé 15 points de dégats au " + monstre.nomMonstre);
                        monstre.attaquer(Personnage joueur, Armure armure);
                    }
                    break;
                case "Eclair":
                    if (joueur.mana < 20) {
                        JOptionPane.showMessageDialog(null, "Vous n'avez pas assez de mana pour lancer ce sort.");
                    } else if (joueur.mana > 20) {
                        joueur.mana -= 20;
                        monstre.PVMonstre -= 30;
                        JOptionPane.showMessageDialog(null, "Vous avez infligé 30 points de dégats au " + monstre.nomMonstre);
                        monstre.attaquer(Personnage joueur, Armure armure);
                    }
                    break;
                case "Glace":
                    if (joueur.mana < 50) {
                        JOptionPane.showMessageDialog(null, "Vous n'avez pas assez de mana pour lancer ce sort.");
                    } else if (joueur.mana > 50) {
                        joueur.mana -= 40;
                        monstre.PVMonstre -= 60;
                        JOptionPane.showMessageDialog(null, "Vous avez infligé 60 points de dégats au " + monstre.nomMonstre);
                        monstre.attaquer(Personnage joueur, Armure armure);
                    }
                    break;
            }
        }
    }

 */
}
