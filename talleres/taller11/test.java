import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashSet;
import java.util.Collections;

public class test {
    static final int SIZE = 12;
    static HashSet<Pair<Integer, Integer>> edges;
    static ArrayList<Pair<Integer, Integer>> caminos;
    static int[] sinEntradas = { 0, 1, 3, 4, 5, 6, 7 };

    public static void main(String[] args) {
        edges = fillEdges();

        DiagraphAM gMatrix = new DiagraphAM(SIZE);
        fillGraph(gMatrix);
        System.out.println("DigraphAM (Matriz de Adyacencia):");
        System.out.println("    getWeight() -> " + convert(testWeight(gMatrix)));
        System.out.println("    getSuccesors() -> " + convert(testSuccesors(gMatrix)));

        DigraphAL gList = new DigraphAL(SIZE);
        fillGraph(gList);
        System.out.println("DigraphAL (Listas de Adyacencia):");
        System.out.println("    getWeight() -> " + convert(testWeight(gList)));
        System.out.println("    getSuccesors() -> " + convert(testSuccesors(gList)));

    }
    static HashSet<Pair<Integer, Integer>> fillEdges() {
        HashSet<Pair<Integer, Integer>> edges = new HashSet<>();
        edges.add(Pair.makePair(3, 8));
        edges.add(Pair.makePair(3, 10));
        edges.add(Pair.makePair(5, 11));
        edges.add(Pair.makePair(7, 8));
        edges.add(Pair.makePair(7, 11));
        edges.add(Pair.makePair(8, 9));
        edges.add(Pair.makePair(11, 2));
        edges.add(Pair.makePair(11, 9));
        edges.add(Pair.makePair(11, 10));
        return edges;
    }

    static boolean fillGraph(diagraph g) {
        if (edges == null || g == null)
            return false;
        for (Pair<Integer, Integer> p : edges)
            g.addArc(p.first, p.second, 1);
        return true;
    }

    static boolean testWeight(diagraph g) {
        int w;
        for (int i = 0; i < SIZE; ++i)
            for (int j = 0; j < SIZE; ++j) {
                w = g.getWeight(i, j);
                if (edges.contains(Pair.makePair(i, j))) {
                    if (w != 1)
                        return false;
                } else {
                    if (w != 0)
                        return false;
                }
            }
        return true;
    }

    static boolean testSuccesors(diagraph g) {
        ArrayList<Integer> sucesores;
        for (int i = 0; i < 12; i++) {
            sucesores = g.getSuccessors(i);
            if (sucesores != null) Collections.sort(sucesores);
            switch(i) {
                case 3:
                    if (!sucesores.equals(new ArrayList<Integer>(Arrays.asList(8, 10))))
                        return false;
                    break;
                case 5:
                    if (!sucesores.equals(new ArrayList<Integer>(Arrays.asList(11))))
                        return false;
                    break;
                case 7:
                    if (!sucesores.equals(new ArrayList<Integer>(Arrays.asList(8, 11))))
                        return false;
                    break;
                case 8:
                    if (!sucesores.equals(new ArrayList<Integer>(Arrays.asList(9))))
                        return false;
                    break;
                case 11:
                    if (!sucesores.equals(new ArrayList<Integer>(Arrays.asList(2, 9, 10))))
                        return false;
                    break;
                default:
                    if (sucesores != null)
                        return false;
                    break;
            }
        }
        return true;
    }


    private static ArrayList<Pair<Integer, Integer>> fillCaminos() {
        ArrayList<Pair<Integer, Integer>> caminos = new ArrayList<>();
        caminos.add(Pair.makePair(7, 8));
        caminos.add(Pair.makePair(7, 11));
        caminos.add(Pair.makePair(7, 2));
        caminos.add(Pair.makePair(7, 9));
        caminos.add(Pair.makePair(7, 10));
        caminos.add(Pair.makePair(3, 8));
        caminos.add(Pair.makePair(3, 9));
        caminos.add(Pair.makePair(3, 10));
        caminos.add(Pair.makePair(8, 9));
        caminos.add(Pair.makePair(11, 2));
        caminos.add(Pair.makePair(11, 9));
        caminos.add(Pair.makePair(11, 10));
        caminos.add(Pair.makePair(5, 11));
        caminos.add(Pair.makePair(5, 2));
        caminos.add(Pair.makePair(5, 9));
        caminos.add(Pair.makePair(5, 10));
        return caminos;
    }


    static String convert(boolean b) {
        return b ? "correcta" : "incorrecta";
    }

}
