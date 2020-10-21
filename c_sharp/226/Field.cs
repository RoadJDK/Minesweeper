using System;
using System.Collections.Generic;
using System.Text;

namespace _226
{
    class Field
    {
        public List<Cell> Cells { get; } = new List<Cell>();
        public int BombCount { get; set; } = 8;
        public int FieldCount { get; set; } = 7;
        private Random random = new Random();

        public void printField()
        {
            Console.Write("  ");
            var counter = 0;
            for (var i = 1; i < FieldCount + 1; i++)
            {
                if(i < 10)
                {
                    Console.Write("  " + i);
                } else
                {
                    Console.Write(" " + i);
                }
            }
            Console.WriteLine("");

            for (var i = 1; i < FieldCount + 1; i++)
            {
                if (i > 9)
                {
                    Console.Write(i);
                }
                else
                {
                    Console.Write(i + " ");
                }

                for (var j = 1; j < FieldCount + 1; j++)
                {
                    Console.Write("  " + Cells[counter].printCell(Cells, counter, FieldCount));
                    counter++;
                }
                Console.WriteLine("");
            }
        }

        public void initializeField()
        {
            var randomsCoord = new List<int>();

            for (var i = 0; i < BombCount; i++)
            {
                var x = (random.Next(1, FieldCount).ToString());
                var y = (random.Next(1, FieldCount).ToString());
                if (!randomsCoord.Contains(Convert.ToInt32(x.ToString() + y.ToString())))
                {
                    randomsCoord.Add(Convert.ToInt32(x + y));
                }
                else
                {
                    i--;
                }
            }

            var counter = 0;
            for (var x = 0; x < FieldCount; x++)
            {
                for (var y = 0; y < FieldCount; y++)
                {
                    if (randomsCoord.Contains(Convert.ToInt32((x + 1).ToString() + (y + 1).ToString())))
                    {
                        Cells.Add(new Cell(x + 1, y + 1, Type.BOMB));
                        counter++;
                    }
                    else
                    {
                        Cells.Add(new Cell(x + 1, y + 1, Type.DEFAULT));
                    }
                }
            }
        }
    }
}
