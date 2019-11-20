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
            grid[size - 1][i] = block;
            blockPositions.put(block, i + (size * (size - 1)));
            block++;
        }

        grid[size - 1][blockNumber] = A;
        setAgentPosition(size - 1, size - 1);
    }

    public void generateSolutionGrid()
    {
        int blockNumber = size - 1;
        char block = (char) ('a' + blockNumber - 1);

        blockPositions.clear();

        for (int i = blockNumber; i > 0; i--)
        {
            grid[i][1] = block;
            blockPositions.put(block, 1 + (size * i));
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

    public String printGrid()
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
        if (blockPositions.containsKey(block))
            blockPositions.put(block, x + (size * y));
    }

    private void setAgentPosition(int x, int y)
    {
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
                    grid[y][x] = grid[y][x - 1];
                    grid[y][x - 1] = A;
                    setBlockPosition(grid[y][x], getXCoord(agentPosition), getYCoord(agentPosition));
                    setAgentPosition(x - 1, y);
                    lastDirection = Direction.LEFT;
                }
                break;
            case RIGHT:
                if (x != size - 1)
                {
                    grid[y][x] = grid[y][x + 1];
                    grid[y][x + 1] = A;
                    setBlockPosition(grid[y][x], getXCoord(agentPosition), getYCoord(agentPosition));
                    setAgentPosition(x + 1, y);
                    lastDirection = Direction.RIGHT;
                }
                break;
            case UP:
                if (y != 0)
                {
                    grid[y][x] = grid[y - 1][x];
                    grid[y - 1][x] = A;
                    setBlockPosition(grid[y][x], getXCoord(agentPosition), getYCoord(agentPosition));
                    setAgentPosition(x, y - 1);
                    lastDirection = Direction.UP;
                }
                break;
            case DOWN:
                if (y != size - 1)
                {
                    grid[y][x] = grid[y + 1][x];
                    grid[y + 1][x] = A;
                    setBlockPosition(grid[y][x], getXCoord(agentPosition), getYCoord(agentPosition));
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