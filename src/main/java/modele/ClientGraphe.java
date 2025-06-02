package modele;

public class ClientGraphe {
    public static void main(String[] args) {
        int [] [] tabVoisins = {
                {1, 2},
                {},
                {5},
                {2, 4},
                {5},
                {}
        };

    Graphe g1 = new Graphe(tabVoisins);
    System.out.println(g1.degreeEntrant());
    }
}
