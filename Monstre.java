public class Monstre {
    public String nomMonstre;
    public int PVMonstre;
    public int attaque;
    public int defense;
    public int niveau;
    public int XP;
    public int PV;
    public int esquive;
    public int vitesse;

    public Monstre (String nomMonstre,int PVMonstre, int attaque, int defense, int niveau, int XP, int PV, int esquive, int vitesse){
        this.nomMonstre = nomMonstre;
        this.PVMonstre = PVMonstre;
        this.attaque = attaque;
        this.defense = defense;
        this.niveau = niveau;
        this.XP = XP;
        this.PV = PV;
        this.esquive = esquive;
        this.vitesse = vitesse;
    }
}
