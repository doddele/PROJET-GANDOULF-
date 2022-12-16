public class Arme extends Equipement{
    private String typeEquipement;
    private int attaqueArme;
    public Arme (String typeEquipement, String nom, int attaqueArme, int prix) {
        super(nom, prix);
        this.typeEquipement = typeEquipement;
        this.attaqueArme = attaqueArme;
    }
}