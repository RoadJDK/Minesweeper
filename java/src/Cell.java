import java.util.ArrayList;

public class Cell {

    private int x = 0;
    private int y = 0;
    private Status status = Status.COVERED;
    private Type type = Type.DEFAULT;

    public Cell(int x, int y, Type type)
    {
        this.x = x;
        this.y = y;
        this.type = type;
    }

    public String printCell(ArrayList<Cell> cells, int index, int fieldCount)
    {
        if (status == Status.COVERED)
        {
            return "-";
        }
        else if (status == Status.MARKED)
        {
            return "!";
        }
        else if (status == Status.UNCOVERED && type == Type.BOMB)
        {
            return "*";
        }
        else
        {
            return checkNeighbors(cells, index, fieldCount);
        }
    }

    private int tryCatch(ArrayList<Cell> cells, int index)
    {
        try
        {
            if (cells.get(index).type == Type.BOMB)
            {
                return 1;
            } else
            {
                return 0;
            }
        } catch (Exception e)
        {
            return 0;
        }
    }

    private String checkNeighbors(ArrayList<Cell> cells, int index, int fieldCount)
    {
        var counter = 0;
        var forbidden = new ArrayList<Integer>();
        for (var i = 0; i < fieldCount; i++)
        {
            forbidden.add((fieldCount*i) - 1);
        }

        if(!forbidden.contains(index))
        {
            counter += tryCatch(cells, index - (fieldCount - 1));
            counter += tryCatch(cells, index - fieldCount);
            counter += tryCatch(cells, index - (fieldCount + 1));

            counter += tryCatch(cells, index - 1);
            counter += tryCatch(cells, index + 1);

            counter += tryCatch(cells, index + (fieldCount - 1));
            counter += tryCatch(cells, index + fieldCount);
            counter += tryCatch(cells, index + (fieldCount + 1));
        } else
        {
            counter += tryCatch(cells, (index - fieldCount) - 1);
            counter += tryCatch(cells, index - fieldCount);

            counter += tryCatch(cells, index - 1);

            counter += tryCatch(cells, (index + fieldCount) - 1);
            counter += tryCatch(cells, index + fieldCount);
        }

        return Integer.toString(counter);
    }

    public Status getStatus() {
        return status;
    }

    public void setStatus(Status status) {
        this.status = status;
    }

    public int getY() {
        return y;
    }

    public int getX() {
        return x;
    }

    public Type getType() {
        return type;
    }
}
