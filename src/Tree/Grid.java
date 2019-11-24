package Tree;

import java.util.HashMap;
import java.util.Map;

public class Grid
{
    private final int size;
    private final char[][] grid;
    private int agentPosition;
    private Direction lastDirection = null;
    private Map<Character, Integer> blockPositions = new HashMap<>();

    private static final char A = '¬';
    private static final char E = 'W';

    public Grid(int size)
    {
        this.size = size;
        this.grid = new char[size][size];

        for (int i = 0; i < size; i++)
        {
            for (int j = 0; j < size; j++)
            {
                grid[i][j] = E;
            }
        }
    }

    public Grid(char[][] grid, int agentPosition, Map<Character, Integer> blockPositions)
    {
        this.size = grid.length;
        this.grid = grid;
        this.agentPosition = agentPosition;
        this.blockPositions = blockPositions;
    }

    public void generateStartingGrid()
    {
        int blockNumber = size - 1;
        char block = 'a';

        blockPositions.clear();

        for (int i = 0; i < blockNumber; i++)
        {
            setBlockPosition(block, i, size - 1);
            block++;
        }

        setAgentPosition(size - 1, size - 1);
    }

    // IMPORTANT: This function only works if the size of the grid is 4x4, i.e. size = 4.
    public void generateStartingGrid(int depth)
    {
        blockPositions.clear();

        switch (depth)
        {
            case 0:
                setBlockPosition('a', 1, 1);
                setBlockPosition('b', 1, 2);
                setBlockPosition('c', 1, 3);
                setAgentPosition(2, 1);
                break;
            case 1:
                setBlockPosition('a', 1, 1);
                setBlockPosition('b', 0, 2);
                setBlockPosition('c', 1, 3);
                setAgentPosition(1, 2);
                break;
            case 2:
                setBlockPosition('a', 0, 1);
                setBlockPosition('b', 1, 1);
                setBlockPosition('c', 1, 3);
                setAgentPosition(1, 2);
                break;
            case 3:
                setBlockPosition('a', 2, 1);
                setBlockPosition('b', 1, 1);
                setBlockPosition('c', 1, 2);
                setAgentPosition(1, 3);
                break;
            case 4:
                setBlockPosition('a', 2, 1);
                setBlockPosition('b', 1, 1);
                setBlockPosition('c', 1, 2);
                setAgentPosition(2, 3);
                break;
            case 5:
                setBlockPosition('a', 2, 1);
                setBlockPosition('b', 1, 1);
                setBlockPosition('c', 1, 2);
                setAgentPosition(2, 2);
                break;
            case 6:
                setBlockPosition('a', 2, 1);
                setBlockPosition('b', 1, 1);
                setBlockPosition('c', 1, 2);
                setAgentPosition(0, 1);
                break;
            case 7:
                setBlockPosition('a', 2, 1);
                setBlockPosition('b', 1, 1);
                setBlockPosition('c', 1, 2);
                setAgentPosition(0, 0);
                break;
            case 8:
                setBlockPosition('a', 2, 1);
                setBlockPosition('b', 1, 1);
                setBlockPosition('c', 1, 2);
                setAgentPosition(1, 0);
                break;
            case 9:
                setBlockPosition('a', 1, 3);
                setBlockPosition('b', 1, 2);
                setBlockPosition('c', 2, 3);
                setAgentPosition(0, 3);
                break;
            case 10:
                setBlockPosition('a', 0, 3);
                setBlockPosition('b', 1, 2);
                setBlockPosition('c', 2, 3);
                setAgentPosition(1, 3);
                break;
            case 11:
                setBlockPosition('a', 0, 3);
                setBlockPosition('b', 1, 3);
                setBlockPosition('c', 2, 3);
                setAgentPosition(1, 2);
                break;
            case 12:
                setBlockPosition('a', 0, 3);
                setBlockPosition('b', 1, 3);
                setBlockPosition('c', 2, 3);
                setAgentPosition(0, 2);
                break;
            case 13:
                setBlockPosition('a', 0, 3);
                setBlockPosition('b', 1, 3);
                setBlockPosition('c', 2, 3);
                setAgentPosition(0, 1);
                break;
            case 14:
                generateStartingGrid();
                break;
            case 15:
                setBlockPosition('a', 2, 1);
                setBlockPosition('b', 1, 1);
                setBlockPosition('c', 0, 1);
                setAgentPosition(0, 0);
                break;
            case 16:
                setBlockPosition('a', 0, 3);
                setBlockPosition('b', 1, 3);
                setBlockPosition('c', 3, 3);
                setAgentPosition(2, 2);
                break;
            case 17:
                setBlockPosition('a', 0, 3);
                setBlockPosition('b', 1, 3);
                setBlockPosition('c', 3, 3);
                setAgentPosition(2, 1);
                break;
            case 18:
                setBlockPosition('a', 0, 3);
                setBlockPosition('b', 1, 3);
                setBlockPosition('c', 3, 3);
                setAgentPosition(2, 0);
                break;
            case 19:
                setBlockPosition('a', 0, 3);
                setBlockPosition('b', 1, 3);
                setBlockPosition('c', 3, 3);
                setAgentPosition(0, 0);
                break;
            case 20:
                setBlockPosition('a', 0, 3);
                setBlockPosition('b', 1, 3);
                setBlockPosition('c', 3, 2);
                setAgentPosition(0, 0);
                break;
        }
    }

    public void generateSolutionGrid()
    {
        int blockNumber = size - 1;
        char block = (char) ('a' + blockNumber - 1);

        blockPositions.clear();

        for (int i = blockNumber; i > 0; i--)
        {
            setBlockPosition(block, 1, i);
            block--;
        }
    }

    public char[][] getGrid()
    {
        char[][] newGrid = new char[size][size];

        for (int i = 0; i < size; i++)
        {
            System.arraycopy(grid[i], 0, newGrid[i], 0, grid[i].length);
        }

        return newGrid;
    }

    @Override
    public String toString()
    {
        String printString = "";

        for (int i = 0; i < size; i++)
        {
            for (int j = 0; j < size; j++)
            {
                printString = printString + grid[i][j];

                if (j != size - 1)
                    printString = printString + " ";
            }

            printString = printString + "\n";
        }

        return printString;
    }

    private void setBlockPosition(char block, int x, int y)
    {
        grid[y][x] = block;

        if (block != E && block != A)
        {
            blockPositions.put(block, x + (size * y));
        }
    }

    private void setAgentPosition(int x, int y)
    {
        grid[y][x] = A;
        agentPosition = x + (size * y);
    }

    private int getXCoord(int position)
    {
        return position % size;
    }

    private int getYCoord(int position)
    {
        return position / size;
    }

    public int getAgentPosition() { return agentPosition; }

    private int getBlockPosition(char block)
    {
        if (blockPositions.containsKey(block))
            return blockPositions.get(block);

        return -1;
    }

    public Map<Character, Integer> getBlockPositions()
    {
        return blockPositions;
    }

    public void moveAgent(Direction direction)
    {
        int x = getXCoord(agentPosition);
        int y = getYCoord(agentPosition);

        switch (direction)
        {
            case LEFT:
                if (x != 0)
                {
                    setBlockPosition(grid[y][x - 1], getXCoord(agentPosition), getYCoord(agentPosition));
                    setAgentPosition(x - 1, y);
                    lastDirection = Direction.LEFT;
                }
                break;
            case RIGHT:
                if (x != size - 1)
                {
                    setBlockPosition(grid[y][x + 1], getXCoord(agentPosition), getYCoord(agentPosition));
                    setAgentPosition(x + 1, y);
                    lastDirection = Direction.RIGHT;
                }
                break;
            case UP:
                if (y != 0)
                {
                    setBlockPosition(grid[y - 1][x], getXCoord(agentPosition), getYCoord(agentPosition));
                    setAgentPosition(x, y - 1);
                    lastDirection = Direction.UP;
                }
                break;
            case DOWN:
                if (y != size - 1)
                {
                    setBlockPosition(grid[y + 1][x], getXCoord(agentPosition), getYCoord(agentPosition));
                    setAgentPosition(x, y + 1);
                    lastDirection = Direction.DOWN;
                }
                break;
        }
    }

    public Direction getLastDirection()
    {
        return lastDirection;
    }

    public boolean equals(Grid solution)
    {
        boolean equal;

        for (int i = 0; i < size; i++)
        {
            for (int j = 0; j < size; j++)
            {
                equal = grid[i][j] == solution.grid[i][j];

                if (!equal && grid[i][j] != A)
                    return false;
            }
        }

        return true;
    }

    public int calculateManhattanDistance(Grid solution)
    {
        int distance = 0;

        for (Map.Entry entry : blockPositions.entrySet())
        {
            int gridPosition = (int)entry.getValue();
            int solutionPosition = solution.getBlockPosition((char)entry.getKey());

            int gridX = getXCoord(gridPosition);
            int gridY = getYCoord(gridPosition);
            int solutionX = getXCoord(solutionPosition);
            int solutionY = getYCoord(solutionPosition);

            distance = distance + Math.abs(gridX - solutionX) + Math.abs(gridY - solutionY);
        }

        return distance;
    }
}