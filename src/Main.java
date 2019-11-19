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

        Scanner scanner = new Scanner(System.in);
        System.out.println("Please insert the appropriate number for the search strategy you wish to use:");
        System.out.println("1. BFS\n2. DFS\n3. DFS with random node order\n4. IDS\n5. A* Search");

        while (strategy == null)
        {
            String input = scanner.nextLine();

            switch (input)
            {
                case "1":
                    strategy = new BFS();
                    break;

                case "2":
                    strategy = new DFS(false);
                    break;

                case "3":
                    strategy = new DFS(true);
                    break;

                case "4":
                    strategy = new IDS();
                    break;

                case "5":
                    strategy = new AStar();
                    break;

                default:
                    System.out.println(input + " is not a valid number! Please enter the number that corresponds to the search strategy you wish to use.");
                    System.out.println("1. BFS\n2. DFS\n3. DFS with random node order\n4. IDS\n5. A* Search");
                    break;
            }
        }

        Node solution = strategy.search();
        int nodesVisited = solution.getNodeNumber();

        List<Direction> directions = new ArrayList<>();

        System.out.println("\n" + solution.getValue().printGrid());

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
    }

    /** public static void optimalSolution()
    {
        Grid grid = new Grid(4);
        grid.generateStartingGrid();

        grid.moveAgent(Tree.Direction.UP);
        System.out.println(grid.printGrid());
        grid.moveAgent(Tree.Direction.LEFT);
        System.out.println(grid.printGrid());
        grid.moveAgent(Tree.Direction.LEFT);
        System.out.println(grid.printGrid());
        grid.moveAgent(Tree.Direction.DOWN);
        System.out.println(grid.printGrid());
        grid.moveAgent(Tree.Direction.LEFT);
        System.out.println(grid.printGrid());
        grid.moveAgent(Tree.Direction.UP);
        System.out.println(grid.printGrid());
        grid.moveAgent(Tree.Direction.RIGHT);
        System.out.println(grid.printGrid());
        grid.moveAgent(Tree.Direction.DOWN);
        System.out.println(grid.printGrid());
        grid.moveAgent(Tree.Direction.RIGHT);
        System.out.println(grid.printGrid());
        grid.moveAgent(Tree.Direction.UP);
        System.out.println(grid.printGrid());
        grid.moveAgent(Tree.Direction.UP);
        System.out.println(grid.printGrid());
        grid.moveAgent(Tree.Direction.LEFT);
        System.out.println(grid.printGrid());
        grid.moveAgent(Tree.Direction.DOWN);
        System.out.println(grid.printGrid());
        grid.moveAgent(Tree.Direction.LEFT);
        System.out.println(grid.printGrid());
    } **/
}
