import java.util.Random;
import java.io.*;
import java.io.FileInputStream;
import java.io.IOException;
import java.util.Scanner;

public class testMonstres {
    public static void main(String[] args) {

        //List listMonstres = Arrays.asList(listMonstres);

        Monstres Valstrax = new Monstres("Valstrax", 100, 120, 150, 200, 850, 12, 52); //appelle le contenu de la classe Monstre et utilise ses données


        try {
            //fichier decrivant les monstres
            //faire un tirage aléatoire sur le monstre rencontré
            String chemin = System.getProperty("user.dir") + "\\src\\Monstres.txt";
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

            Monstres monstre = new Monstres(info[0], Integer.parseInt(info[1]), Integer.parseInt(info[2]), Integer.parseInt(info[3]), Integer.parseInt(info[4]), Integer.parseInt(info[5]), Integer.parseInt(info[6]), Integer.parseInt(info[7]));
            //on appelle le monstre correspondant à la ligne et ses cara sont attribués


            //TODO rajouter un para sur le fichier.txt pour l'int vitesse (8/8) -> fait

            System.out.println("Nom du monstre : " + monstre.nomMonstre);
            System.out.println("Points de vie du monstre : " + monstre.PVMonstre);
            System.out.println("Attaque du monstre : " + monstre.AttaqueMonstre);
            System.out.println("Défense du monstre : " + monstre.DefenseMonstre);
            System.out.println("Niveau du monstre : " + monstre.NiveauMonstre);
            System.out.println("Vitesse du monstre : " + monstre.VitesseMonstre);
            System.out.println("Points d'XP rapportés par le monstre : " + monstre.XPdonneMonstre);
            System.out.println("Esquive du monstre: " + monstre.EsquiveMonstre);


        } catch (IOException e) {
            e.printStackTrace();
        }

    }

}










