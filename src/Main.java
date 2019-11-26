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
        String inputString;
        boolean debugMode;

        System.out.println("Would you like to turn on debug mode? (Y/N)");

        inputString = scanner.nextLine();

        while (!inputString.toUpperCase().equals("Y") && !inputString.toUpperCase().equals("N"))
        {
            System.out.println("Please enter a valid input - i.e. either Y for yes, or N for no");
            inputString = scanner.nextLine();
        }

        debugMode = inputString.toUpperCase().equals("Y");

        System.out.println("Please insert the appropriate number for the search strategy you wish to use:");
        System.out.println("1. BFS\n2. DFS\n3. DFS with random node order\n4. IDS\n5. A* Search");

        // Set the strategy to the search that the user has specified.
        while (strategy == null)
        {
            input = scanner.nextInt();

            switch (input)
            {
                case 1:
                    strategy = new BFS(debugMode);
                    strategyName = "Breadth First Search (BFS)";
                    break;

                case 2:
                    strategy = new DFS(false, debugMode);
                    strategyName = "Depth First Search (BFS)";
                    break;

                case 3:
                    strategy = new DFS(true, debugMode);
                    strategyName = "Depth First Search (DFS), with random node order";
                    break;

                case 4:
                    strategy = new IDS(debugMode);
                    strategyName = "Iterative Deepening Search (IDS)";
                    break;

                case 5:
                    strategy = new AStar(debugMode);
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

        // Verify that the optimal depth selected is allowed. If not, re-prompt the user for their input.
        while (input < 0 || input > 20)
        {
            System.out.println("Error: " + input + " is not a valid value. The value specified must be between 0 and 20.");
            input = scanner.nextInt();
        }

        // Set the starting grid based on the optimal depth value provided.
        strategy.setStartingGrid(input);

        System.out.println("\n*************************************************************************************");
        System.out.println(strategyName + " - Optimal Solution Depth: " + input);
        System.out.println("*************************************************************************************\n");

        System.out.println("Root:\n" + strategy.getStartingGrid());

        // Perform the search.
        Node solution = strategy.search();
        int nodesGenerated = strategy.getNodesGenerated();
        int depth = solution.getDepth();

        List<Grid> path = new ArrayList<>();
        List<Direction> directions = new ArrayList<>();

        // Retrieve the steps taken by analysing each node, storing the grid, and then moving up the tree.
        while (solution.getParent() != null)
        {
            path.add(solution.getValue());
            solution = solution.getParent();
        }

        // Reverse the path, since it will be in the opposite order because we're travelling up the tree.
        Collections.reverse(path);

        int i = 1;

        // Output each node grid state.
        for (Grid grid : path)
        {
            System.out.println("Depth " + i + ":\n" + grid);
            directions.add(grid.getLastDirection());
            i++;
        }

        System.out.print("Solution found after generating " + nodesGenerated + " nodes: ");

        i = 1;

        // Output the steps taken.
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
