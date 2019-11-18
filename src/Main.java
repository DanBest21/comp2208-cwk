import Search.BFS;
import Tree.Direction;
import Tree.Node;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

public class Main
{
    public static void main(String[] args)
    {
        performBFS();
    }

    public static void performBFS()
    {
        BFS bfs = new BFS();

        Node solution = bfs.search();
        List<Direction> directions = new ArrayList<>();

        System.out.println(solution.getValue().printGrid());

        while (solution.getParent() != null)
        {
            directions.add(solution.getValue().getLastDirection());
            solution = solution.getParent();
        }

        Collections.reverse(directions);

        System.out.print("Solution found: ");

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

    /** public void sampleSolution(Tree.Grid grid)
    {
        grid.moveAgent(Tree.Direction.UP);
        grid.moveAgent(Tree.Direction.LEFT);
        grid.moveAgent(Tree.Direction.LEFT);
        grid.moveAgent(Tree.Direction.DOWN);
        grid.moveAgent(Tree.Direction.RIGHT);
        grid.moveAgent(Tree.Direction.UP);
        grid.moveAgent(Tree.Direction.UP);
        grid.moveAgent(Tree.Direction.LEFT);
        grid.moveAgent(Tree.Direction.LEFT);
        grid.moveAgent(Tree.Direction.DOWN);
        grid.moveAgent(Tree.Direction.DOWN);
        grid.moveAgent(Tree.Direction.RIGHT);
        grid.moveAgent(Tree.Direction.RIGHT);
        grid.moveAgent(Tree.Direction.UP);
        grid.moveAgent(Tree.Direction.UP);
        grid.moveAgent(Tree.Direction.LEFT);
        grid.moveAgent(Tree.Direction.LEFT);
        grid.moveAgent(Tree.Direction.DOWN);
        grid.moveAgent(Tree.Direction.DOWN);
        grid.moveAgent(Tree.Direction.RIGHT);
        grid.moveAgent(Tree.Direction.RIGHT);
        grid.moveAgent(Tree.Direction.UP);
        grid.moveAgent(Tree.Direction.UP);
        grid.moveAgent(Tree.Direction.LEFT);
        grid.moveAgent(Tree.Direction.LEFT);
        grid.moveAgent(Tree.Direction.UP);
        grid.moveAgent(Tree.Direction.RIGHT);
        grid.moveAgent(Tree.Direction.RIGHT);
        grid.moveAgent(Tree.Direction.DOWN);
        grid.moveAgent(Tree.Direction.DOWN);
        grid.moveAgent(Tree.Direction.DOWN);
        grid.moveAgent(Tree.Direction.LEFT);
        grid.moveAgent(Tree.Direction.LEFT);
        grid.moveAgent(Tree.Direction.UP);
        grid.moveAgent(Tree.Direction.UP);
        grid.moveAgent(Tree.Direction.UP);
        grid.moveAgent(Tree.Direction.RIGHT);
        grid.moveAgent(Tree.Direction.RIGHT);
        grid.moveAgent(Tree.Direction.DOWN);
        grid.moveAgent(Tree.Direction.DOWN);
        grid.moveAgent(Tree.Direction.DOWN);
        grid.moveAgent(Tree.Direction.LEFT);
        grid.moveAgent(Tree.Direction.LEFT);
    } **/
}
