public class Grid
{
    private final int size;
    private final char[][] grid;
    private int agentPosition;
    private Direction lastDirection = null;

    private static final char A = '¬';

    public Grid(int size)
    {
        this.size = size;
        this.grid = new char[size][size];

        for (int i = 0; i < size; i++)
        {
            for (int j = 0; j < size; j++)
            {
                grid[i][j] = 'W';
            }
        }
    }

    public Grid(char[][] grid, int agentPosition)
    {
        this.size = grid.length;
        this.grid = grid;
        this.agentPosition = agentPosition;
    }

    public void generateStartingGrid()
    {
        int blockNumber = size - 1;
        char block = 'a';

        for (int i = 0; i < blockNumber; i++)
        {
            grid[size - 1][i] = block;
            block++;
        }

        grid[size - 1][blockNumber] = A;
        setAgentPosition(size - 1, size - 1);
    }

    public void generateSolutionGrid()
    {
        int blockNumber = size - 1;
        char block = (char) ('a' + blockNumber - 1);

        for (int i = blockNumber; i > 0; i--)
        {
            grid[i][1] = block;
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

    private void setAgentPosition(int x, int y)
    {
        agentPosition = x + (size * y);
    }

    private int getAgentXCoord()
    {
        return agentPosition % size;
    }

    private int getAgentYCoord()
    {
        return agentPosition / size;
    }

    public int getAgentPosition() { return agentPosition; }

    public char[][] moveAgent(Direction direction)
    {
        int x = getAgentXCoord();
        int y = getAgentYCoord();

        switch (direction)
        {
            case LEFT:
                if (x != 0)
                {
                    grid[y][x] = grid[y][x - 1];
                    grid[y][x - 1] = A;
                    setAgentPosition(x - 1, y);
                    lastDirection = Direction.LEFT;
                }
                break;
            case RIGHT:
                if (x != size - 1)
                {
                    grid[y][x] = grid[y][x + 1];
                    grid[y][x + 1] = A;
                    setAgentPosition(x + 1, y);
                    lastDirection = Direction.RIGHT;
                }
                break;
            case UP:
                if (y != 0)
                {
                    grid[y][x] = grid[y - 1][x];
                    grid[y - 1][x] = A;
                    setAgentPosition(x, y - 1);
                    lastDirection = Direction.UP;
                }
                break;
            case DOWN:
                if (y != size - 1)
                {
                    grid[y][x] = grid[y + 1][x];
                    grid[y + 1][x] = A;
                    setAgentPosition(x, y + 1);
                    lastDirection = Direction.DOWN;
                }
                break;
        }

        return grid;
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
}