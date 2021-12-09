package api;

import api.DirectedWeightedGraph;
import api.DirectedWeightedGraphAlgorithms;

import java.util.Scanner;

/**
 * This class is the main class for Ex2 - your implementation will be tested using this class.
 */
public class Ex2 {
    /**
     * This static function will be used to test your implementation
     * @param json_file - a json file (e.g., G1.json - G3.gson)
     * @return
     */
    public static DirectedWeightedGraph getGrapg(String json_file) {
        DirectedWeightedGraph ans = new DirectedG();
        return ans;
    }
    /**
     * This static function will be used to test your implementation
     * @param json_file - a json file (e.g., G1.json - G3.gson)
     * @return
     */
    public static DirectedWeightedGraphAlgorithms getGrapgAlgo(String json_file) {
        DirectedWeightedGraphAlgorithms ans = new DirectedGAlgo();
        ans.load(json_file);
        return ans;
    }
    /**
     * This static function will run your GUI using the json fime.
     * @param json_file - a json file (e.g., G1.json - G3.gson)
     *
     */
    public static void runGUI(String json_file) {
        DirectedWeightedGraphAlgorithms alg = getGrapgAlgo(json_file);

    }

    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        DirectedGAlgo algo = new DirectedGAlgo();
        while (algo.g1.vertix.size()==0) {
            System.out.println("Enter file name:");
            String file_name = sc.nextLine();
            algo.load(file_name);
        }
            System.out.println("Graph loaded successfully");
            GUI win = new GUI(algo);
    }
}