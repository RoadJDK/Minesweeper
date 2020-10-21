import java.util.Scanner;

public class Controller {

    private Field field = new Field();
    Scanner in = new Scanner(System.in);

    public void run()
    {
        setupField();
        initialize();
        playLoop();
    }

    private void setupField()
    {
        System.out.println("Welcome to minesweeper!");
        System.out.println("");
        System.out.println("How large should your field be? (easymode = 7)");
        try
        {
            field.setFieldCount(Integer.parseInt(in.nextLine()));
        }
        catch (Exception e)
        {
            run();
        }
        System.out.println("How much bombs should your field have? (easymode = 8)");
        try
        {
            field.setBombCount(Integer.parseInt(in.nextLine()));
        }
        catch (Exception e)
        {
            run();
        }
    }

    private int countUncovered()
    {
        var count = 0;

        for (var i = 0; i < field.getCells().size(); i++)
        {
            if (field.getCells().get(i).getStatus() == Status.UNCOVERED)
            {
                count++;
            }
        }
        return count;
    }

    private void playLoop()
    {
        String input;
        System.out.println("to mark a field type: M x y");
        System.out.println("to uncover a field type: T x y");
        System.out.println("to exit type: exit");
        System.out.println("");

        do
        {
            field.printField();

            if (countUncovered() >= (field.getCells().size() - field.getBombCount()))
            {
                winGame();
            }


            System.out.println("");
            input = in.nextLine();
            handleInput(input);

        } while (input != "exit");

        System.out.println("cya!");
    }

    private Cell searchCellInListOrNull(int x, int y) {
        for (Cell cell : field.getCells()) {
            if(cell.getX() == x && cell.getY() == y) {
                return cell;
            }
        }
        return null;
    }

    private void handleInput(String input)
    {
        var inputArray = input.split(" ");
            if(inputArray[0].equals("M"))
            {
                var cell = searchCellInListOrNull(Integer.parseInt(inputArray[2]), Integer.parseInt(inputArray[1]));
                cell.setStatus(Status.MARKED);

            } else if(inputArray[0].equals("T"))
            {
                var cell = searchCellInListOrNull(Integer.parseInt(inputArray[2]), Integer.parseInt(inputArray[1]));
                cell.setStatus(Status.UNCOVERED);

                if (cell.getType() == Type.BOMB)
                {
                    for (var i = 0; i < field.getCells().size(); i++)
                    {
                        field.getCells().get(i).setStatus(Status.UNCOVERED);
                    }
                    field.printField();
                    gameOver();
                }
            }
        }

    private void gameOver()
    {
        System.out.println("Game Over");
        System.exit(0);
    }

    private void winGame()
    {
        System.out.println("YOU WON");
        System.exit(0);
    }

    private void initialize()
    {
        field.initializeField();
    }
}
