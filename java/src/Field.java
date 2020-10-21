import java.util.ArrayList;
import java.util.Random;

public class Field {

    private ArrayList<Cell> cells = new ArrayList<>();
    private int bombCount = 8;
    private int fieldCount = 7;
    private Random random = new Random();

    public void printField()
    {
        System.out.print("  ");
        var counter = 0;
        for (var i = 1; i < fieldCount + 1; i++)
        {
            if(i < 10)
            {
                System.out.print("  " + i);
            } else
            {
                System.out.print(" " + i);
            }
        }
        System.out.println("");

        for (var i = 1; i < fieldCount + 1; i++)
        {
            if (i > 9)
            {
                System.out.print(i);
            }
            else
            {
                System.out.print(i + " ");
            }

            for (var j = 1; j < fieldCount + 1; j++)
            {
                System.out.print("  " + cells.get(counter).printCell(cells, counter, fieldCount));
                counter++;
            }
            System.out.println("");
        }
    }

    public void initializeField()
    {
        var randomsCoord = new ArrayList<Integer>();

        for (var i = 0; i < bombCount; i++)
        {
            var x = (Integer.toString(random.nextInt(fieldCount)+1));
            var y = (Integer.toString(random.nextInt(fieldCount)+1));
            if (!randomsCoord.contains(Integer.parseInt(x + y)))
            {
                randomsCoord.add(Integer.parseInt(x + y));
            }
            else
            {
                i--;
            }
        }

        var counter = 0;
        for (var x = 0; x < fieldCount; x++)
        {
            for (var y = 0; y < fieldCount; y++)
            {
                if (randomsCoord.contains(Integer.parseInt(Integer.toString(x + 1) + Integer.toString(y + 1))))
                {
                    cells.add(new Cell(x + 1, y + 1, Type.BOMB));
                    counter++;
                }
                else
                {
                    cells.add(new Cell(x + 1, y + 1, Type.DEFAULT));
                }
            }
        }
    }

    public ArrayList<Cell> getCells() {
        return cells;
    }

    public void setBombCount(int bombCount) {
        this.bombCount = bombCount;
    }

    public int getBombCount() {
        return bombCount;
    }

    public void setFieldCount(int fieldCount) {
        this.fieldCount = fieldCount;
    }

}
