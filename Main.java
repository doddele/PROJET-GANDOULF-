import org.w3c.dom.ls.LSOutput;

import javax.swing.*;
import java.io.*;
import java.nio.Buffer;
import java.nio.file.*;
import java.util.*;
import java.lang.Math;

public class Main {
    /**
     * Lecture de l'équipement.
     */
    static void lectureEquipement() {
        try {
            // Fichier d'entrée.
            FileInputStream equipements = new FileInputStream(
                    "/home/jules/Desktop/PROJET GANDOULF/Ressources/equipements.txt");
            Scanner scanner = new Scanner(equipements);

            // Renvoie true s'il y a une autre ligne à lire.
            while (scanner.hasNextLine()) {

                // Prend une ligne du fichier texte à la fois et stocke la ligne dans "equipementLigne".
                String equipementLigne = scanner.nextLine();

                // Sépare les données de chaque perso et les stocke dans une liste.
                String[] equipementSplit = equipementLigne.split(";");

                // Ajoute chaque donnée à une variable.
                String a = equipementSplit[0];
                String b = equipementSplit[1];
                int c = Integer.parseInt(equipementSplit[2]);
                int d = Integer.parseInt(equipementSplit[3]);

                /*
                // Crée une arme ou une armure en fonction des données.
                if (Objects.equals(equipementSplit[0], "arme")) {
                    Arme test = new Arme(a, b, c, d);
                    System.out.println("type:" + a + "\tnom:" + b + "\tattaque:" + c + "\tprix:" + d);
                } else {
                    Armure test2 = new Armure(a, b, c, d);
                    System.out.println("type:" + a + "\tnom:" + b + "\tdefense:" + c + "\tprix:" + d);
                }


                 */
            }

            // Ferme le scanner.
            scanner.close();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    static void resetMarchand() {

        // Ajout de 3 items aléatoires dans le fichier équipement temporaire.
        try {

            // Fichier d'entrée.
            File inputFile = new File("/home/jules/Desktop/PROJET GANDOULF/Ressources/equipements.txt");

            // Fichier temporaire.
            File tempFileMarchand = new File("tempFileMarchand.txt");

            BufferedReader reader = new BufferedReader(new FileReader(inputFile));
            BufferedWriter writer = new BufferedWriter(new FileWriter(tempFileMarchand));

            String typeArme;
            String currentLine;

            int compteur = 1;
            int tailleEquip = 8;

            // Tire trois nombres aléatoires.
            Random random = new Random();
            int nb1 = random.nextInt(8);
            int nb2 = random.nextInt(8);
            int nb3 = random.nextInt(8);

            // Sort de la boucle seulement si les trois nombres sont différents.
            while (true) {
                if (nb1 == nb2) {
                    nb2 = random.nextInt(8);
                }
                if (nb1 == 0) {
                    nb2 = random.nextInt(8);
                }
                if (nb2 == 0) {
                    nb2 = random.nextInt(8);
                }
                if (nb3 == nb1) {
                    nb3 = random.nextInt(8);
                }
                if (nb3 == nb2) {
                    nb3 = random.nextInt(8);
                }
                if (nb3 == 0) {
                    nb3 = random.nextInt(8);
                }
                if (nb1 != nb2) {
                    if (nb1 != nb3) {
                        if (nb1 != 0) {
                            if (nb2 != 0) {
                                if (nb3 != 0) {
                                    break;
                                }
                            }
                        }
                    }
                }
            }

            // Copie du fichier équipement dans le fichier marchand temporaire.
            while ((currentLine = reader.readLine()) != null) {  // Continue tant qu'il y a des lignes.

                String trimmedLine = currentLine.trim();

                // Split la ligne selon les ";" et la stocke dans la variable "a".
                String[] a = trimmedLine.split(";");

                if (nb1 == compteur) {
                    // Si le numéro de la ligne est égal au numéro aléatoire, enregistre
                    // les données de l'item dans une nouvelle ligne.
                    writer.write(currentLine + System.getProperty("line.separator"));
                }

                if (nb2 == compteur) {
                    // Si le numéro de la ligne est égal au numéro aléatoire, enregistre
                    // les données de l'item dans une nouvelle ligne.
                    writer.write(currentLine + System.getProperty("line.separator"));
                }

                if (nb3 == compteur) {
                    // Si le numéro de la ligne est égal au numéro aléatoire, enregistre
                    // les données de l'item dans une nouvelle ligne.
                    writer.write(currentLine + System.getProperty("line.separator"));
                }

                compteur += 1;
            }

            writer.close();
            reader.close();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    /**
     * Déroulement du jeu.
     */
    static void gameRun() {
        JFrame f = new JFrame();

        String nom = "";
        Personnage joueur = new Personnage(nom);
        Boolean gameRun = true;
        int var = 0;


        String[] introOptions = {"Créer un nouveau personnage", "Charger un personnage pré-existant"};
        int intro = JOptionPane.showOptionDialog(null, "Que voulez-vous faire ?",
                "Bienvenue dans Projet Gandoulf !", JOptionPane.DEFAULT_OPTION, -1,
                null, introOptions, introOptions[0]);

        loop:
        switch (intro) {
            case 0:     // Créer un nouveau personnage.
                String[] classeOptions = {"Barbare", "Sorcier"};
                int classe = JOptionPane.showOptionDialog(null, "Choisissez une classe.",
                        "Bienvenue dans Projet Gandoulf !", JOptionPane.DEFAULT_OPTION, -1,
                        null, classeOptions, classeOptions[0]);
                switch (classe) {

                    // Choix de la classe.
                    case 0:     // Création Barbare.
                        // Fais passer le joueur de Personnage à Barbare.
                        joueur = new Barbare(nom);

                        // Permet d'entrer un nom.
                        joueur.creerPerso();
                        joueur.save();
                        break loop;

                    case 1:     // Création Sorcier.
                        // Fait passer le joueur de Personnage à Sorcier.
                        joueur = new Sorcier(nom);

                        // Permet d'entrer un nom.
                        joueur.creerPerso();
                        joueur.save();
                        break loop;
                }
            case 1:     // Charger un personnage préexistant.

                int choixPersoMenu = 0;
                try {

                    // Fichier d'entrée.
                    FileInputStream personnages = new FileInputStream(
                            "/home/jules/Desktop/PROJET GANDOULF/Ressources/personnages.txt");
                    Scanner scanner = new Scanner(personnages);

                    // Liste qui va contenir les noms des personnages disponibles.
                    List<String> listeNomsPersos = new ArrayList<>();
                    List<String> listeStatPersos = new ArrayList<>();


                    // Renvoie true s'il y a une autre ligne à lire.
                    while (scanner.hasNextLine()) {
                        int compteur = 0;

                        // Prend une ligne du fichier texte à la fois et stocke la ligne dans "persoDispo".
                        String persoDispo = scanner.nextLine();

                        // Sépare les données de chaque perso et les stocke dans une liste.
                        String[] persosSplit = persoDispo.split(";");

                        // Ajoute le nom, la classe et le niveau du personnage dans l'arraylist "listeNomPersos".
                        listeNomsPersos.add(persosSplit[0] + " - " + persosSplit[1] + " niveau: " + persosSplit[2]);
                        listeStatPersos.add(persosSplit[compteur]);
                    }

                    // Transforme les arraylist en array.
                    String[] optionChoixPerso = listeNomsPersos.toArray(new String[0]);

                    choixPersoMenu = JOptionPane.showOptionDialog(null, "Choisissez un personnage:",
                            "Personnages", JOptionPane.DEFAULT_OPTION, -1, null, optionChoixPerso,
                            optionChoixPerso[0]);

                    scanner.close();
                } catch (IOException e) {
                    e.printStackTrace();
                }

                // String qui va comporter les statistiques du personnage.
                String statPerso = "";
                String[] statPersoSplit = {};

                // Lecture des stats du personnage choisi.
                try {
                    // Fichier d'entrée.
                    FileInputStream personnages = new FileInputStream(
                            "/home/jules/Desktop/PROJET GANDOULF/Ressources/personnages.txt");
                    Scanner scanner = new Scanner(personnages);

                    // Indexe la ligne du personnage choisi
                    for (int i = 0; i <= choixPersoMenu; i++) {
                        if (i == choixPersoMenu) {

                            // Ajoute les statistiques du personnages a une string.
                            statPerso += scanner.nextLine();

                            // Split les statistiques du personnage.
                            statPersoSplit = statPerso.split(";");
                        }
                        if (scanner.hasNextLine()) {     // Passe à la ligne suivante s'il y en a une.
                            scanner.nextLine();
                        }
                    }
                    scanner.close();

                    // Choisi le constructeur du personnage en fonction de la classe.
                    switch (statPersoSplit[1]) {        // StatPersoSplit[1] correspond à la classe.
                        case "Barbare":
                            nom = statPersoSplit[0];

                            // Instancie un Barbare avec les statistiques du fichier txt.
                            joueur = new Barbare(nom,                       // nom
                                    Integer.parseInt(statPersoSplit[2]),    // niveau
                                    Integer.parseInt(statPersoSplit[3]),    // XP
                                    Integer.parseInt(statPersoSplit[4]),    // HPmax
                                    Integer.parseInt(statPersoSplit[5]),    // HP
                                    Integer.parseInt(statPersoSplit[6]),    // attaque
                                    Integer.parseInt(statPersoSplit[7]),    // defense
                                    Integer.parseInt(statPersoSplit[8]),    // esquive
                                    Double.parseDouble(statPersoSplit[9]),  // critique
                                    Integer.parseInt(statPersoSplit[10]),   // vitesse
                                    statPersoSplit[11],                     // arme1
                                    statPersoSplit[12],                     // arme2
                                    statPersoSplit[13],                     // armure
                                    Integer.parseInt(statPersoSplit[14]),   // or
                                    Integer.parseInt(statPersoSplit[15]));  // nbPotions
                            System.out.println("nom:" + joueur.nom +
                                    " niveau:" + joueur.level +
                                    " XP:" + joueur.XP +
                                    " HPmax:" + joueur.HPmax +
                                    " HP:" + joueur.HP +
                                    " attaque:" + joueur.attaque +
                                    " defense:" + joueur.defense +
                                    " critique:" + joueur.critique);
                            var = Integer.parseInt(statPersoSplit[6]);
                            break;
                        case "Sorcier":
                            nom = statPersoSplit[0];

                            // Instancie un Barbare avec les statistiques du fichier txt.
                            joueur = new Sorcier(nom,                       // nom
                                    Integer.parseInt(statPersoSplit[2]),    // niveau
                                    Integer.parseInt(statPersoSplit[3]),    // XP
                                    Integer.parseInt(statPersoSplit[4]),    // HPmax
                                    Integer.parseInt(statPersoSplit[5]),    // HP
                                    Integer.parseInt(statPersoSplit[6]),    // mana
                                    Integer.parseInt(statPersoSplit[7]),    // manaMax
                                    Integer.parseInt(statPersoSplit[8]),    // attaque
                                    Integer.parseInt(statPersoSplit[9]),    // defense
                                    Integer.parseInt(statPersoSplit[10]),   // esquive
                                    Integer.parseInt(statPersoSplit[11]),   // vitesse
                                    statPersoSplit[12],                     // arme
                                    statPersoSplit[13],                     // armure
                                    Integer.parseInt(statPersoSplit[14]),   // or
                                    Integer.parseInt(statPersoSplit[15]),   // nbPotions
                                    statPersoSplit[16]);                    // sorts
                            break;
                    }
                } catch (IOException e) {
                    e.printStackTrace();
                }
        }

        while (gameRun) {
            // Menu principal du jeu.
            String[] menuOptions = {"Combat de monstres", "Arène", "Combat entre joueurs", "Marchand",
                    "Sauvegarder"};
            int menu = JOptionPane.showOptionDialog(null, "\nQue voulez-vous faire ?",
                    joueur.nom + " - " + joueur.classe + " niveau: " + joueur.level,
                    JOptionPane.DEFAULT_OPTION, -1, null, menuOptions,
                    menuOptions[0]);

            // Affichage du menu principal du jeu.
            switch (menu) {


                case 0:     // Combat de monstre.


                    System.out.println(joueur.attaque);
                    System.out.println(joueur.classe);
                    System.out.println(joueur.mana);
                    System.out.println(var);

                    // Instanciation du monstre.
                    Monstre monstre = new Monstre("Zeph", 100, 1, 1, 1, 1, 100, 1, 1);
                    JOptionPane.showMessageDialog(null,
                            "Un " + monstre.nomMonstre + " sauvage apparait !", "Combat", -1);

                    if (joueur.vitesse < monstre.vitesse) { // Détermine qui du joueur ou du monstre va attaquer le premier.
                        joueur.HP = joueur.HP - (monstre.attaque - joueur.defense);
                    }
                    int nbtours = 0;
                    do {
                        String[] menuCombatdemonstre = {"Attaquer", "Potion", "Lancer un sort"};
                        int combat = JOptionPane.showOptionDialog(null,
                                "Quelle action souhaitez-vous effectuer ?", joueur.nom + " HP: " + joueur.HP + "/" +
                                        joueur.HPmax +  "  -  " + monstre.nomMonstre + " HP: " + monstre.PVMonstre,
                                JOptionPane.DEFAULT_OPTION, -1, null, menuCombatdemonstre,
                                menuCombatdemonstre[0]);
                        switch (combat) {
                            case 0:     // Attaquer.
                                monstre.PVMonstre = monstre.PVMonstre - (joueur.attaque - monstre.defense);
                                if (monstre.PVMonstre < 0) {
                                    monstre.PVMonstre = 0;
                                    System.out.println("Bravo ! Vous avez triomphé de votre adversaire !");
                                }
                                nbtours += 1;
                                break;
                            case 1:     // Potion.
                                if (joueur.nbPotions > 0) {
                                    Potion potion = new Potion(1, 1, 1);
                                    Potion.getPotion(joueur);
                                    joueur.HP = joueur.HP + potion.soinPV;
                                    joueur.nbPotions -= 1;
                                } else {
                                    System.out.println("Vous n'avez pas de potions.");
                                }
                                nbtours += 1;
                                break;
                            case 2:     // Lancer un sort.
                                if (Objects.equals(joueur.classe,"Sorcier")) {       // TODO: instancier joueur.mana
                                    if (nbtours == 0) {
                                        monstre.PVMonstre = monstre.PVMonstre - (15 - monstre.defense);
                                        joueur.mana -= 10;
                                    } else {
                                        if (joueur.mana >= 5) {
                                            monstre.PVMonstre = monstre.PVMonstre - (5 - monstre.defense);
                                            joueur.mana -= 5;
                                        } else {
                                            monstre.PVMonstre = (int) (monstre.PVMonstre - ((5 * 0.90) - monstre.defense));
                                        }
                                    }
                                    nbtours += 1;
                                    break;
                                } else {
                                    System.out.println("Pas de sort disponible.");
                                }
                                break;
                        }
                    }
                    while (joueur.HP > 0 && monstre.PVMonstre > 0);


                    resetMarchand();    // A placer après le combat pour avoir un loot différent chez le marchand après chaque combat.
                    break;


                case 1:     // Arène.

                    // TODO: Combat en arène
                    //resetMarchand();
                    break;


                case 2:     // Combat entre joueurs.

                    // TODO: Combat JvJ
                    //resetMarchand();
                    break;


                case 3:     // Marchand.

                    // Affichage du marchand.
                    String[] marchandOptions = {"Equipements", "Potions", "Vendre"};
                    int marchand = JOptionPane.showOptionDialog(null,
                            "Vous avez " + joueur.or + " pièces d'or." +
                                    "\n\nArme: " + joueur.arme + "\nDeuxième arme: " + joueur.arme2 + "\nArmure: " + joueur.armure + "\nNombre de potions: " +
                                    joueur.nbPotions + "\n\nQue voulez-vous acheter ?", "Marchand",
                            JOptionPane.DEFAULT_OPTION, -1, null, marchandOptions,
                            marchandOptions[0]);

                    switch (marchand) {
                        case 0:     // Achat d'équipement.

                            /* TODO: Achat d'équipement -> switch arme/armure dans main, stocker les 3 équipements
                                aléatoires dans fichier temporaire où ils seront lus par le marchand
                             */

                            // Lecture fichier et affichage dans marchand

                            int choixEquiptMenu;

                            try {
                                // Fichier d'entrée.
                                FileInputStream equiptAVendre = new FileInputStream(
                                        "/home/jules/Desktop/PROJET GANDOULF/tempFileMarchand.txt");
                                Scanner scanner = new Scanner(equiptAVendre);

                                // Liste qui va contenir les équipements disponibles.
                                List<String> listeNomsEquipt = new ArrayList<>();
                                List<String> listeStatEquipt = new ArrayList<>();
                                String[] equiptSplit = {};


                                // Renvoie true s'il y a une autre ligne à lire.
                                while (scanner.hasNextLine()) {

                                    // Prend une ligne du fichier texte à la fois et stocke la ligne dans "persoDispo".
                                    String equiptDispo = scanner.nextLine();

                                    // Sépare les données de chaque perso et les stocke dans une liste.
                                    equiptSplit = equiptDispo.split(";");


                                    if (Objects.equals(equiptSplit[0], "armure")) {
                                        // Ajoute le nom, la classe et le niveau du personnage dans l'arraylist "listeNomPersos".
                                        listeNomsEquipt.add(equiptSplit[1] + ", DEF +" + equiptSplit[2] + ", "
                                                + equiptSplit[3] + " pièces d'or.");
                                    } else {
                                        listeNomsEquipt.add(equiptSplit[1] + ", " + "ATK +" + equiptSplit[2] + ", "
                                                + equiptSplit[3] + " pièces d'or");
                                    }
                                }
                                scanner.close();

                                // Transforme les arraylist en array.
                                String[] optionEquiptPerso = listeNomsEquipt.toArray(new String[0]);

                                choixEquiptMenu = JOptionPane.showOptionDialog(null,
                                        "Vous avez " + joueur.or + " pièces d'or."
                                                + "\n\nQue voulez-vous acheter ?",
                                        "Marchand", JOptionPane.DEFAULT_OPTION, -1,
                                        null, optionEquiptPerso, optionEquiptPerso[0]);

                                System.out.println(equiptSplit[0]);

                                switch (choixEquiptMenu) {
                                    default:     // Permet d'acheter une arme choisie..
                                        if(Objects.equals(equiptSplit[0], "arme")){
                                            Arme arme = new Arme(Integer.parseInt(equiptSplit[2]), equiptSplit[1], Integer.parseInt(equiptSplit[3]));
                                            arme.acheterArme(joueur);
                                        }
                                        break;
                                }
                            } catch (IOException e) {
                                e.printStackTrace();
                            }
                            // TODO: Equipement.acheterEquipement();

                            break;

                        case 1:     // Achat de potions.
                            Potion.acheterPotion(joueur);
                            break;

                        case 2:     // Vendre de l'équipement ou des potions.
                            break;
                    }
                    break;


                case 4:     // Sauvegarde.
                    try {

                        // Fichier d'entrée.
                        File inputFile = new File("/home/jules/Desktop/PROJET GANDOULF/Ressources/personnages.txt");

                        // Fichier temporaire.
                        File tempFilePerso = new File("/home/jules/Desktop/PROJET GANDOULF/Ressources/tempFilePerso.txt");

                        BufferedReader reader = new BufferedReader(new FileReader(inputFile));
                        BufferedWriter writer = new BufferedWriter(new FileWriter(tempFilePerso));

                        String lineToRemove = nom;
                        String currentLine;

                        while ((currentLine = reader.readLine()) != null) {  // Continue tant qu'il y a des lignes.
                            String trimmedLine = currentLine.trim();

                            // Split la ligne selon les ";" et la stocke dans la variable "a".
                            String[] a = trimmedLine.split(";");

                            // Si le premier item de "a" correspond au nom du joueur, supprime la ligne et enregistre
                            // les données du personnage dans une nouvelle ligne.
                            if (a[0].equals(lineToRemove)) continue;
                            writer.write(currentLine + System.getProperty("line.separator"));
                            writer.write((joueur.getStat()));
                        }
                        writer.close();
                        reader.close();




                    } catch (IOException e) {
                        e.printStackTrace();
                    }
            }

        }
        // gameRun = false; A AJOUTER quand on quitte la fenêtre
    }


    /**
     * Méthode main.
     */
    public static void main(String[] args) {
        gameRun();
    }
}