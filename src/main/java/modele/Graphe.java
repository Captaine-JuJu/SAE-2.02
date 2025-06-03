package modele;

import java.util.List;
import java.util.Set;
import java.util.TreeMap;
import java.util.TreeSet;

public class Graphe {
    private TreeMap<Integer, TreeSet<Integer>> chMapVoisins;

    public Graphe(int[][] tabVoisins) {
        chMapVoisins = new TreeMap<>();
        for (int indiceSommet = 0; indiceSommet < tabVoisins.length; indiceSommet++) {
            TreeSet<Integer> voisins = new TreeSet<>();
            chMapVoisins.put(indiceSommet, voisins);
            for (int sommetVoisin = 0; sommetVoisin < tabVoisins[indiceSommet].length; sommetVoisin++) {
                voisins.add(tabVoisins[indiceSommet][sommetVoisin]);

            }
        }
    }

    public Set ensembleSommet(){
        return chMapVoisins.keySet();
    }

    public int ordre(){
        return chMapVoisins.keySet().size();
    }

    public int degree(int indiceSommet){
        return chMapVoisins.get(indiceSommet).size();
    }

    public int taille() {
        int taille = 0;
        for (TreeSet<Integer> voisins : chMapVoisins.values()) {
            if (voisins != null) {
                taille += voisins.size();
            }
        }
        return taille;
    }


    public int degreeMax(){
        int degree = 0;
        for (int indiceSommet = 0 ; indiceSommet < this.ordre(); indiceSommet++) {
            if (degree < chMapVoisins.get(indiceSommet).size()) {
                degree = chMapVoisins.get(indiceSommet).size();
            }
        }
        return degree;
    }

    public int degreeMin(){
        int degree = this.ordre();
        for (int indiceSommet = 0 ; indiceSommet < this.ordre(); indiceSommet++) {
            if (degree > chMapVoisins.get(indiceSommet).size()) {
                degree = chMapVoisins.get(indiceSommet).size();
            }
        }
        return degree;
    }

    public TreeMap<Integer, Integer> degreeEntrant() {
        TreeMap<Integer, Integer> degreeEntrants = new TreeMap<>();

        for (Integer sommet : chMapVoisins.keySet()) {
            degreeEntrants.put(sommet, 0);
        }

        for (Integer sommet : chMapVoisins.keySet()) {
            TreeSet<Integer> voisins = chMapVoisins.get(sommet);
            if (voisins != null) {
                for (Integer voisin : voisins) {
                    // Incrémente le degré entrant du voisin
                    degreeEntrants.put(voisin, degreeEntrants.getOrDefault(voisin, 0) + 1);
                }
            }
        }

        return degreeEntrants;
    }


    public String toString() {
        String res = new String();
        res += "ordre : " + this.ordre() + "\n" + "taille : " + this.taille() + "\n" + "degré min : " + this.degreeMin()
                + "\n" + "degree max : " + this.degreeMax() + "\n";
        for (int i = 0 ; i < ordre() ; i++) {
            res += "Sommet : " + i + ", " + "degré : " + chMapVoisins.get(i) + "\n";
        }
        return res;
    }


}
