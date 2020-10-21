using System;
using System.Collections.Generic;
using System.Linq;
using System.Text;

namespace _226
{
    class Controller
    {
        private Field field = new Field();

        public void Run()
        {
            setupField();
            initialize();
            playLoop();
        }

        private void setupField()
        {
            Console.WriteLine("Welcome to minesweeper!");
            Console.WriteLine("");
            Console.WriteLine("How large should your field be? (easymode = 9, hardmode = 16)");
            try
            {
                field.FieldCount = Convert.ToInt32(Console.ReadLine());
            }
            catch (Exception)
            {
                Console.Clear();
                Run();
            }
            Console.WriteLine("How much bombs should your field have? (easymode = 10, hardmode = 40)");
            try
            {
                field.BombCount = Convert.ToInt32(Console.ReadLine());
            }
            catch (Exception)
            {
                Console.Clear();
                Run();
            }
        }

        private int countUncovered()
        {
            var count = 0;

            for (var i = 0; i < field.Cells.Count; i++)
            {
                if (field.Cells[i].Status == Status.UNCOVERED)
                {
                    count++;
                }
            } 
            return count;
        }

        private void playLoop()
        {
            string input;
            Console.WriteLine("to mark a field type: M x y");
            Console.WriteLine("to uncover a field type: T x y");
            Console.WriteLine("to exit type: exit");
            Console.WriteLine("");

            do
            {
                field.printField();

                if (countUncovered() >= (field.Cells.Count - field.BombCount))
                {
                    winGame();
                }

                Console.WriteLine("");
                input = Console.ReadLine();
                handleInput(input);

            } while (input != "exit");

            Console.WriteLine("cya!");
        }

        private void handleInput(string input)
        {
            var inputArray = input.Split(" ");

            try
            {
                if(inputArray[0] == "M")
                {
                    var cell = field.Cells.Find(x => x.X == Convert.ToInt32(inputArray[2]) && x.Y == Convert.ToInt32(inputArray[1]));
                    cell.Status = Status.MARKED;

                } else if(inputArray[0] == "T")
                {
                    var cell = field.Cells.Find(x => x.X == Convert.ToInt32(inputArray[2]) && x.Y == Convert.ToInt32(inputArray[1]));
                    cell.Status = Status.UNCOVERED;

                    if (cell.Type == Type.BOMB)
                    {
                        for (var i = 0; i < field.Cells.Count; i++)
                        {
                            field.Cells[i].Status = Status.UNCOVERED;
                        }
                        field.printField();
                        gameOver();
                    }
                    else
                    {
                    }
                }
            } catch(Exception)
            {
                Console.WriteLine("wrong input");
            }
        }

        private void gameOver()
        {
            Console.WriteLine("Game Over");
            Environment.Exit(0);
        }

        private void winGame()
        {
            Console.WriteLine("YOU WON");
            Environment.Exit(0);
        }

        private void initialize()
        {
            field.initializeField();
        }
    }
}
