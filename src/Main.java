import Search.*;
import Tree.*;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.Scanner;

public class Main
{
    public static void main(String[] args)
    {
        Search strategy = null;
        String strategyName = "";

        Scanner scanner = new Scanner(System.in);
        int input;

        System.out.println("Please insert the appropriate number for the search strategy you wish to use:");
        System.out.println("1. BFS\n2. DFS\n3. DFS with random node order\n4. IDS\n5. A* Search");

        while (strategy == null)
        {
            input = scanner.nextInt();

            switch (input)
            {
                case 1:
                    strategy = new BFS();
                    strategyName = "Breadth First Search (BFS)";
                    break;

                case 2:
                    strategy = new DFS(false);
                    strategyName = "Depth First Search (BFS)";
                    break;

                case 3:
                    strategy = new DFS(true);
                    strategyName = "Depth First Search (DFS), with random node order";
                    break;

                case 4:
                    strategy = new IDS();
                    strategyName = "Iterative Deepening Search (IDS)";
                    break;

                case 5:
                    strategy = new AStar();
                    strategyName = "A* Heuristic Search";
                    break;

                default:
                    System.out.println(input + " is not a valid number! Please enter the number that corresponds to the search strategy you wish to use.");
                    System.out.println("1. BFS\n2. DFS\n3. DFS with random node order\n4. IDS\n5. A* Search");
                    break;
            }
        }

        System.out.println("Please specify the optimal depth (between 0 and 20) of the solution you want to search for:");

        input = scanner.nextInt();

        while (input < 0 || input > 20)
        {
            System.out.println("Error: " + input + " is not a valid value. The value specified must be between 0 and 20.");
            input = scanner.nextInt();
        }

        strategy.setStartingGrid(input);

        Node solution = strategy.search();
        int nodesVisited = solution.getNodeNumber();
        int depth = solution.getDepth();

        List<Direction> directions = new ArrayList<>();

        System.out.println("\n*************************************************************************************");
        System.out.println(strategyName + " - Optimal Solution Depth: " + input);
        System.out.println("*************************************************************************************");

        System.out.println("\n" + solution.getValue());

        while (solution.getParent() != null)
        {
            directions.add(solution.getValue().getLastDirection());
            solution = solution.getParent();
        }

        Collections.reverse(directions);

        System.out.print("Solution found in " + nodesVisited + " nodes: ");

        int i = 1;

        for (Direction dir : directions)
        {
            if (i == directions.size())
                System.out.print(dir.toString());
            else
                System.out.print(dir.toString() + " -> ");

            i++;
        }

        System.out.println("\nDepth of solution found: " + depth);
    }
}
