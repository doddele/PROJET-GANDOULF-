import javax.swing.*;
import java.lang.Math;
import java.util.Objects;
public class Arme extends Equipement implements Marchand{
    public Arme (int attaqueArme, String nom, int prix ) {
        super(nom, prix);
        this.typeEquipement = "arme";
        this.attaqueArme = attaqueArme;
    }
    @Override
    public void acheterPotions(Personnage joueur) {

    }

    @Override
    public void vendrePotions() {

    }
    @Override
    public void acheterEquipement(Personnage joueur) {

    }

    @Override
    public void vendreEquipement() {

    }
    @Override
    public void acheterArme(Personnage joueur) {
        int or = joueur.getOr();
        if (Objects.equals(joueur.classe, "Voleur")) {
            if (joueur.or >= (this.prix / 2)) {
                joueur.setOr(joueur.or - this.prix / 2);
                joueur.setArme(this.nom);
                JOptionPane.showMessageDialog(null, "Vous avez acheté l'arme " + this.nom + ".");
                Arme arme1 = new Arme(this.attaqueArme, this.nom, this.prix);
            }
            else{
                JOptionPane.showMessageDialog(null, "Vous n'avez pas assez d'argent pour acheter cet article!",
                        "Marchand", -1);
            }
        } else {
            if (joueur.or >= this.prix) {
                joueur.setOr(joueur.or - this.prix);
                if (Objects.equals(joueur.classe, "Barbare")) {       // Permet au Barbare de choisir quelle arme il veut remplacer
                    String[] choixArmeBarbare = {joueur.arme, joueur.arme2};

                    int choixArme = JOptionPane.showOptionDialog(null, "Quelle arme voulez-vous remplacer ?",
                            "Marchand", JOptionPane.DEFAULT_OPTION, -1, null, choixArmeBarbare,
                            choixArmeBarbare[0]);

                    switch (choixArme) {
                        case 0:
                            joueur.setArme(this.nom);
                            JOptionPane.showMessageDialog(null, "Vous avez acheté l'arme " + this.nom + ".");
                            Arme arme1 = new Arme(this.attaqueArme, this.nom, this.prix);
                            break;
                        case 1:
                            joueur.setArme2(this.nom);
                            JOptionPane.showMessageDialog(null, "Vous avez acheté l'arme " + this.nom + ".");
                            Arme arme2 = new Arme(this.attaqueArme, this.nom, this.prix);
                            break;
                    }
                } else {
                    joueur.setArme(this.nom);
                    JOptionPane.showMessageDialog(null, "Vous avez acheté l'arme " + this.nom + ".");
                    Arme arme1 = new Arme(this.attaqueArme, this.nom, this.prix);
                }
            } else {
                JOptionPane.showMessageDialog(null, "Vous n'avez pas assez d'argent pour acheter cet article!",
                        "Marchand", -1);
            }
        }
    }
}