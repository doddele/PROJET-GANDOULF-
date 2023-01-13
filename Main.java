import org.w3c.dom.ls.LSOutput;

import javax.swing.*;
import javax.swing.ImageIcon;
import java.io.*;
import java.nio.Buffer;
import java.nio.file.*;
import java.util.*;
import java.lang.Math;
import java.nio.file.Files;

public class Main {
    private static void randomMonstre(){
        Monstre monstre = new Monstre();

        try {
            //fichier decrivant les monstres
            //faire un tirage aléatoire sur le monstre rencontré
            String chemin = "/home/jules/Desktop/PROJET GANDOULF/Ressources/monstres.txt";
            FileInputStream file = new FileInputStream(chemin);
            Scanner scanner = new Scanner(file);
            Random random = new Random();
            int nb;
            nb = random.nextInt(14);
            String line = "";
            // ligne du monstre et scinder ses différentes cara qui sont séparées par des points virgules)
            int compteur = 0;
            //TODO faire une boucle qui fait que si le niveau du monstre est supérieur de 3 niveaux à celui du joueur, alors on relance un tirage aléatoire
            while (compteur <= nb) {
                line = scanner.nextLine();
                compteur++;
                System.out.println(line);
            }
            scanner.close();

            String[] info = line.split(";");
            System.out.println(info);
            System.out.println("Attention ! Un " + info[0] + " ! Attention à ses attaques dévastatrices !");

            monstre = new Monstre(
                    info[0],                        // nom
                    Integer.parseInt(info[1]),      // PV
                    Integer.parseInt(info[2]),      // attaque
                    Integer.parseInt(info[3]),      // defense
                    Integer.parseInt(info[4]),      // niveau
                    Integer.parseInt(info[5]),      // XP
                    Integer.parseInt(info[6]),      // PV
                    Integer.parseInt(info[7]),      // esquive
                    Integer.parseInt(info[8]));     // vitesse
            //on appelle le monstre correspondant à la ligne et ses cara sont attribués

        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    /**
     * Copie un fichier dans un autre.
     *
     * @param source
     * @param dest
     * @throws IOException
     */
    private static void copyFile(File source, File dest) throws IOException {
        InputStream is = null;
        OutputStream os = null;
        try {
            is = new FileInputStream(source);
            os = new FileOutputStream(dest);
            byte[] buffer = new byte[1024];
            int length;
            while ((length = is.read(buffer)) > 0) {
                os.write(buffer, 0, length);
            }
        } finally {
            is.close();
            os.close();
        }
    }

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

            // Tire trois nombres aléatoires différents les uns des autres.
            Random random = new Random();

            int nb1 = random.nextInt(8);
            while (nb1 == 0) {
                nb1 = random.nextInt(8);
            }

            int nb2 = random.nextInt(8);
            while (nb2 == 0 || nb1 == nb2) {
                nb2 = random.nextInt(8);
            }

            int nb3 = random.nextInt(8);

            while (nb3 == 0 || nb3 == nb1 || nb3 == nb2) {
                nb3 = random.nextInt(8);
            }

            System.out.println(nb1);
            System.out.println(nb2);
            System.out.println(nb3);


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

        ImageIcon iconic = new ImageIcon("/home/jules/Desktop/PROJET GANDOULF/Ressources/underworld.jpg");


        String[] introOptions = {"Créer un nouveau personnage", "Charger un personnage pré-existant"};
        int intro = JOptionPane.showOptionDialog(f, "",
                "Bienvenue dans Projet Gandoulf !", JOptionPane.DEFAULT_OPTION, -1,
                iconic, introOptions, introOptions[0]);

        loop:
        switch (intro) {
            case 0:     // Créer un nouveau personnage.

                ImageIcon character = new ImageIcon("/home/jules/Desktop/PROJET GANDOULF/Ressources/dd_character.jpg");

                String[] classeOptions = {"Barbare", "Sorcier"};
                int classe = JOptionPane.showOptionDialog(f, "Choisissez une classe.",
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

                    character = new ImageIcon("/home/jules/Desktop/PROJET GANDOULF/Ressources/dd_character.jpg");   // TODO: Image plus petite

                    choixPersoMenu = JOptionPane.showOptionDialog(f, "Choisissez un personnage:",
                            "Personnages", JOptionPane.DEFAULT_OPTION, -1, null, optionChoixPerso,
                            optionChoixPerso[0]);


/*
                    String choiPersoMenu = (String) JOptionPane.showInputDialog(null, "Choisissez un personnage:",
                            "Personnages", JOptionPane.QUESTION_MESSAGE, null, optionChoixPerso,
                            optionChoixPerso[0]);

 */

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

            System.out.println(joueur.defense);
            System.out.println(joueur.arme);
            System.out.println(joueur.arme2);
            System.out.println(joueur.armure);

            System.out.println("xp actuel:" + joueur.XP);

            Arme arme1 = new Arme(0, "Vide", 0);
            switch (joueur.arme) {        // Instancie l'arme 1 du joueur.
                case "Vide":
                    arme1 = new Arme(0, "Vide", 0);
                    break;
                case "Baton":
                    arme1 = new Arme(5, "Baton", 30);
                    break;
                case "Epée rudimentaire":
                    arme1 = new Arme(10, "Epée rudimentaire", 50);
                    break;
                case "Hache cassée":
                    arme1 = new Arme(12, "Hache cassée", 70);
                    break;
                case "Epée en acier":
                    arme1 = new Arme(50, "Hache cassée", 1000);
                    break;
            }

            Arme arme2 = new Arme(0, "Vide", 0);
            switch (joueur.arme2) {        // Instancie l'arme 2 du joueur.ù
                case "Vide":
                    arme2 = new Arme(0, "Vide", 0);
                    break;
                case "Baton":
                    arme2 = new Arme(5, "Baton", 30);
                    break;
                case "Epée rudimentaire":
                    arme2 = new Arme(10, "Epée rudimentaire", 50);
                    break;
                case "Hache cassée":
                    arme2 = new Arme(12, "Hache cassée", 70);
                    break;
                case "Epée en acier":
                    arme2 = new Arme(50, "Hache cassée", 1000);
                    break;
            }

            Armure armure = new Armure(0, "Vide", 0);
            switch (joueur.armure) {
                case "Vide":
                    armure = new Armure(0, "Vide", 0);
                    break;
                case "Tunique rudimentaire":
                    armure = new Armure(3, "Tunique rudimentaire", 30);
                    break;
                case "Armure en cuir":
                    armure = new Armure(5, "Armure en cuir", 50);
                    break;
                case "Tunique d'aventurier":
                    armure = new Armure(10, "Armure en cuir", 100);
                    break;
                case "Armure en acier":
                    armure = new Armure(30, "Armure en acier", 1000);
                    break;
            }

            System.out.println("attaque arme 1:" + arme1.attaqueArme);
            System.out.println("attaque arme 2:" + arme2.attaqueArme);
            System.out.println("armure:" + armure.defenseArme);


            // Affichage du menu principal du jeu.
            switch (menu) {


                case 0:     // Combat de monstre.


                    // TODO: Faire pop le monstre
                    Monstre monstre = new Monstre();

                    int niveauMonstre=1000;
                    do {
                        try {
                            //fichier decrivant les monstres
                            //faire un tirage aléatoire sur le monstre rencontré
                            String chemin = "/home/jules/Desktop/PROJET GANDOULF/Ressources/monstres.txt";
                            FileInputStream file = new FileInputStream(chemin);
                            Scanner scanner = new Scanner(file);
                            Random random = new Random();
                            int nb;
                            nb = random.nextInt(14);
                            String line = "";
                            // ligne du monstre et scinder ses différentes cara qui sont séparées par des points virgules)
                            int compteur = 0;
                            //TODO faire une boucle qui fait que si le niveau du monstre est supérieur de 3 niveaux à celui du joueur, alors on relance un tirage aléatoire
                            while (compteur <= nb) {
                                line = scanner.nextLine();
                                compteur++;
                                System.out.println(line);
                            }
                            scanner.close();

                            String[] info = line.split(";");
                            System.out.println(info);
                            System.out.println("Attention ! Un " + info[0] + " ! Attention à ses attaques dévastatrices !");

                            monstre = new Monstre(
                                    info[0],                        // nom
                                    Integer.parseInt(info[1]),      // PV
                                    Integer.parseInt(info[2]),      // attaque
                                    Integer.parseInt(info[3]),      // defense
                                    Integer.parseInt(info[4]),      // niveau
                                    Integer.parseInt(info[5]),      // XP
                                    Integer.parseInt(info[6]),      // PV
                                    Integer.parseInt(info[7]),      // esquive
                                    Integer.parseInt(info[8]));     // vitesse
                            //on appelle le monstre correspondant à la ligne et ses cara sont attribués
                            niveauMonstre = monstre.niveau;

                        } catch (IOException e) {
                            e.printStackTrace();
                        }
                    }
                    while(niveauMonstre>(joueur.level+3));


                    System.out.println(joueur.attaque);
                    System.out.println(joueur.classe);
                    System.out.println(joueur.mana);
                    System.out.println(var);

                    int atkArme1 = arme1.attaqueArme;
                    int atkArme2 = arme2.attaqueArme;
                    int defArmure = armure.defenseArme;
                    System.out.println("atkarm1: " + atkArme1);

                    // Instanciation du monstre.
                    // Monstre monstre = new Monstre("Zeph", 200, 10, 0, 1, 100, 100, 1, 10, 100);
                    JOptionPane.showMessageDialog(null,
                            "Un " + monstre.nomMonstre + " sauvage apparait !", "Combat", -1);

                    loop:
                    while (joueur.HP > 0 || monstre.PVMonstre > 0) {
                        String[] menuCombatdemonstre = {"Attaquer", "Potion", "Lancer un sort"};
                        int combat = JOptionPane.showOptionDialog(null,
                                "Quelle action souhaitez-vous effectuer ?", joueur.nom + " HP: " + joueur.HP + "/" +
                                        joueur.HPmax + "  -  " + monstre.nomMonstre + " HP: " + monstre.PVMonstre,
                                JOptionPane.DEFAULT_OPTION, -1, null, menuCombatdemonstre,
                                menuCombatdemonstre[0]);
                        switch (combat) {

                            case 0:     // Attaquer.

                                if (joueur.vitesse > monstre.vitesse) {     // Combat si le joueur est plus rapide.

                                    // Attaque du joueur.
                                    if (Objects.equals(joueur.classe, "Barbare")) {
                                        double nb;
                                        nb = Math.random();
                                        nb = nb * 100;
                                        System.out.println("nb=" + nb);
                                        if (nb < joueur.critique) {
                                            monstre.PVMonstre = monstre.PVMonstre - (joueur.attaque + atkArme1 + atkArme2 - monstre.defense) * 2;

                                            JOptionPane.showMessageDialog(null, "Coup critique ! " +
                                                    joueur.nom + " a infligé " + (joueur.attaque + atkArme1 + atkArme2 - monstre.defense) * 2 + " dégâts au " + monstre.nomMonstre);
                                        } else {
                                            monstre.PVMonstre = monstre.PVMonstre - (joueur.attaque + atkArme1 + atkArme2 - monstre.defense);

                                            JOptionPane.showMessageDialog(null, joueur.nom + " a infligé " +
                                                    (joueur.attaque + atkArme1 + atkArme2 - monstre.defense) + " dégâts au " + monstre.nomMonstre);
                                        }
                                    } else {
                                        monstre.PVMonstre = monstre.PVMonstre - (joueur.attaque + atkArme1 - monstre.defense);

                                        JOptionPane.showMessageDialog(null, joueur.nom + " a infligé " +
                                                (joueur.attaque + atkArme1 + atkArme2 - monstre.defense) + " dégâts au " + monstre.nomMonstre);
                                    }

                                    // Condition de victoire et brise la boucle.
                                    if (monstre.PVMonstre <= 0) {
                                        JOptionPane.showMessageDialog(f, "Le " + monstre.nomMonstre + " sauvage à été battu !");
                                        joueur.setOr(joueur.or + monstre.or);
                                        joueur.setXP(joueur.XP + monstre.XP);
                                        JOptionPane.showMessageDialog(f, "Vous avez gagné " + monstre.or +
                                                " pièces d'or et " + monstre.XP + " points d'expérience.");
                                        joueur.checkXP();
                                        break loop;
                                    }

                                    // Attaque du monstre.
                                    double nb;
                                    nb = Math.random();
                                    nb = nb * 100;
                                    if (nb < joueur.esquive) {
                                        JOptionPane.showMessageDialog(null, "Le " +
                                                monstre.nomMonstre + " sauvage tente d'attaquer, mais " + joueur.nom +
                                                " a esquivé le coup !");
                                    } else {
                                        int atkMonstre = monstre.attaque - joueur.defense - armure.defenseArme;

                                        if (atkMonstre <= 0) {  // Empêche d'avoir des dégâts négatifs.
                                            atkMonstre = 0;
                                        }
                                        joueur.HP -= atkMonstre;
                                        JOptionPane.showMessageDialog(null,
                                                "Le " + monstre.nomMonstre + " sauvage attaque, il inflige " +
                                                        atkMonstre + " points de dégats");
                                    }
                                    if (joueur.HP <= 0) {
                                        JOptionPane.showMessageDialog(null, "Vous êtes mort.");
                                        break loop;
                                    }
                                }

                                // TODO: joueur moins rapide que monstre.
                                break;

                            case 1:     // Potion.
                                if (joueur.nbPotions > 0) {
                                    Potion potion = new Potion(1, 1, 1);
                                    if (joueur.level < 10) {    // si niveau joueur <10, permet d'acheter une potion niveau 1.
                                        potion = new Potion(1, 50, 100);
                                    }
                                    if (joueur.level > 10 && joueur.level < 20) {
                                        potion = new Potion(2, 100, 150);
                                    }
                                    if (joueur.level > 20 && joueur.level < 30) {
                                        potion = new Potion(3, 150, 250);
                                    }
                                    if (joueur.level > 30 && joueur.level < 40) {
                                        potion = new Potion(4, 200, 350);
                                    }
                                    if (joueur.level > 40 && joueur.level < 50) {
                                        potion = new Potion(5, 250, 400);
                                    }
                                    if (joueur.level > 50 && joueur.level < 60) {
                                        potion = new Potion(6, 300, 550);
                                    }
                                    if (joueur.level > 60 && joueur.level < 70) {
                                        potion = new Potion(7, 350, 650);
                                    }
                                    if (joueur.level > 70 && joueur.level < 80) {
                                        potion = new Potion(8, 400, 750);
                                    }
                                    if (joueur.level > 80 && joueur.level < 90) {
                                        potion = new Potion(9, 450, 850);
                                    }
                                    if (joueur.level > 90 && joueur.level < 100) {
                                        potion = new Potion(10, 500, 1000);
                                    }
                                    joueur.HP = joueur.HP + potion.soinPV;
                                    System.out.println("Soin: " + potion.soinPV);
                                    if (joueur.HP > joueur.HPmax) {
                                        joueur.HP = joueur.HPmax;
                                    }
                                    joueur.nbPotions -= 1;
                                    JOptionPane.showMessageDialog(null, joueur.nom + " utilise une potion de soin.");

                                    // Attaque du monstre.
                                    double nb;
                                    nb = Math.random();
                                    nb = nb * 100;
                                    if (nb < joueur.esquive) {
                                        JOptionPane.showMessageDialog(null, "Le " +
                                                monstre.nomMonstre + " sauvage tente d'attaquer, mais " + joueur.nom +
                                                " a esquivé le coup !");
                                    } else {
                                        int atkMonstre = monstre.attaque - joueur.defense - armure.defenseArme;

                                        if (atkMonstre <= 0) {  // Empêche d'avoir des dégâts négatifs.
                                            atkMonstre = 0;
                                        }
                                        joueur.HP -= atkMonstre;
                                        JOptionPane.showMessageDialog(null,
                                                "Le " + monstre.nomMonstre + " sauvage attaque, il inflige " +
                                                        atkMonstre + " points de dégats");
                                    }
                                    if (joueur.HP <= 0) {
                                        JOptionPane.showMessageDialog(null, "Vous êtes mort.");
                                        break loop;
                                    }
                                } else {
                                    JOptionPane.showMessageDialog(null, "Vous n'avez pas de potions.");
                                }
                                break;
                            case 2:     // Lancer un sort.
                                if (Objects.equals(joueur.classe, "Sorcier")) {      // TODO: Sorts du sorcier

                                    break;
                                } else {
                                    JOptionPane.showMessageDialog(null, "Pas de sort disponibles.");
                                }
                                break;
                        }
                    }

                    joueur.soinHP();
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

                            int compteur = 1;
                            Equipement item1 = new Equipement();
                            Equipement item2 = new Equipement();
                            Equipement item3 = new Equipement();

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

                                    // Prend une ligne du fichier texte à la fois et stocke la ligne dans "equiptDispo".
                                    String equiptDispo = scanner.nextLine();

                                    // Sépare les données de chaque equipement et les stocke dans une liste.
                                    equiptSplit = equiptDispo.split(";");

                                    if (Objects.equals(equiptSplit[0], "armure")) {
                                        // Ajoute les stats de l'equipement dans l'arraylist "listeNomEquipt".
                                        listeNomsEquipt.add(equiptSplit[1] + ", DEF +" + equiptSplit[2] + ", "
                                                + equiptSplit[3] + " pièces d'or.");
                                        switch (compteur) {
                                            case 1:
                                                item1 = new Armure(Integer.parseInt(equiptSplit[2]), equiptSplit[1], Integer.parseInt(equiptSplit[3]));
                                                break;
                                            case 2:
                                                item2 = new Armure(Integer.parseInt(equiptSplit[2]), equiptSplit[1], Integer.parseInt(equiptSplit[3]));
                                                break;
                                            case 3:
                                                item3 = new Armure(Integer.parseInt(equiptSplit[2]), equiptSplit[1], Integer.parseInt(equiptSplit[3]));
                                                break;
                                        }
                                    } else {
                                        listeNomsEquipt.add(equiptSplit[1] + ", " + "ATK +" + equiptSplit[2] + ", "
                                                + equiptSplit[3] + " pièces d'or");
                                        switch (compteur) {
                                            case 1:
                                                item1 = new Arme(Integer.parseInt(equiptSplit[2]), equiptSplit[1], Integer.parseInt(equiptSplit[3]));
                                                break;
                                            case 2:
                                                item2 = new Arme(Integer.parseInt(equiptSplit[2]), equiptSplit[1], Integer.parseInt(equiptSplit[3]));
                                                break;
                                            case 3:
                                                item3 = new Arme(Integer.parseInt(equiptSplit[2]), equiptSplit[1], Integer.parseInt(equiptSplit[3]));
                                                break;
                                        }
                                    }
                                    compteur += 1;
                                }
                                scanner.close();


                                String a = item1.typeEquipement;
                                String b = item1.nom;
                                int c = item1.attaqueArme;
                                int d = item1.prix;

                                System.out.println("type:" + a + "\tnom:" + b + "\tattaque:" + c + "\tprix:" + d);

                                a = item2.typeEquipement;
                                b = item2.nom;
                                c = item2.attaqueArme;
                                d = item2.prix;

                                System.out.println("type:" + a + "\tnom:" + b + "\tattaque:" + c + "\tprix:" + d);


                                // Transforme les arraylist en array.
                                String[] optionEquiptPerso = listeNomsEquipt.toArray(new String[0]);

                                int choixEquiptMenu = JOptionPane.showOptionDialog(null,
                                        "Vous avez " + joueur.or + " pièces d'or."
                                                + "\n\nQue voulez-vous acheter ?",
                                        "Marchand", JOptionPane.DEFAULT_OPTION, -1,
                                        null, optionEquiptPerso, optionEquiptPerso[0]);

                                switch (choixEquiptMenu) {
                                    case 0:
                                        if (Objects.equals(item1.typeEquipement, "arme")) {
                                            item1.acheterArme(joueur);
                                        } else if (Objects.equals(item1.typeEquipement, "armure")) {
                                            item1.acheterArmure(joueur);
                                        }
                                        break;
                                    case 1:
                                        if (Objects.equals(item2.typeEquipement, "arme")) {
                                            item2.acheterArme(joueur);
                                        } else if (Objects.equals(item2.typeEquipement, "armure")) {
                                            item2.acheterArmure(joueur);
                                        }
                                        break;
                                    case 2:
                                        if (Objects.equals(item3.typeEquipement, "arme")) {
                                            item3.acheterArme(joueur);
                                        } else if (Objects.equals(item3.typeEquipement, "armure")) {
                                            item3.acheterArmure(joueur);
                                        }
                                        break;
                                }
                            } catch (IOException e) {
                                e.printStackTrace();
                            }

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

                        copyFile(tempFilePerso, inputFile);
                        JOptionPane.showMessageDialog(null, "Partie Sauvegardée");


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