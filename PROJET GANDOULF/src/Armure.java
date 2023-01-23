import javax.swing.*;
import java.lang.Math;
import java.util.Objects;

public class Armure extends Equipement {

    public Armure(int defenseArme, String nom, int prix) {
        super(nom, prix);
        this.typeEquipement = "armure";
        this.defenseArme = defenseArme;
    }

    @Override
    public void acheterArmure(Personnage joueur) {
        int or = joueur.getOr();
        if (Objects.equals(joueur.classe, "Voleur")) {
            if (joueur.or >= (this.prix / 2)) {
                joueur.setOr(joueur.or - this.prix / 2);
                joueur.setArmure(this.nom);
                JOptionPane.showMessageDialog(null, "Vous avez acheté l'armure " + this.nom + ".");
            }
            else {
                JOptionPane.showMessageDialog(null, "Vous n'avez pas assez d'argent pour acheter cet article!",
                        "Marchand", -1);
            }
        } else {
            if (joueur.or >= this.prix) {
                joueur.setOr(joueur.or - this.prix);
                joueur.setArmure(this.nom);
                JOptionPane.showMessageDialog(null, "Vous avez acheté l'armure " + this.nom + ".");
            } else {
                JOptionPane.showMessageDialog(null, "Vous n'avez pas assez d'argent pour acheter cet article!",
                        "Marchand", -1);
            }
        }
    }
}
