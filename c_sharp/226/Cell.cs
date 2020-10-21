using System;
using System.Collections.Generic;
using System.Text;

namespace _226
{
    class Cell
    {
        public int X { get; set; } = 0;
        public int Y { get; set; } = 0;
        public Status Status { get; set; } = Status.COVERED;
        public Type Type { get; set; } = Type.DEFAULT;

        public Cell(int x, int y, Type type)
        {
            X = x;
            Y = y;
            Type = type;
        }

        public String printCell(List<Cell> cells, int index, int fieldCount)
        {
            if (Status == Status.COVERED)
            {
                return "-";
            }
            else if (Status == Status.MARKED)
            {
                return "!";
            }
            else if (Status == Status.UNCOVERED && Type == Type.BOMB)
            {
                return "*";
            }
            else
            {
                return checkNeighbors(cells, index, fieldCount);
            }
        }

        private int tryCatch(List<Cell> cells, int index)
        {
            try
            {
                if (cells[index].Type == Type.BOMB)
                {
                    return 1;
                } else
                {
                    return 0;
                }
            } catch (Exception)
            {
                return 0;
            }
        }

        private String checkNeighbors(List<Cell> cells, int index, int fieldCount)
        {
            var counter = 0;
            var forbidden = new List<int>();
            for (var i = 0; i < fieldCount; i++)
            {
                forbidden.Add((fieldCount*i) - 1);
            }

            if(!forbidden.Contains(index))
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
            
            return counter.ToString();
        }
    }
}
