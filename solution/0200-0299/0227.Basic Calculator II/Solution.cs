using System.Collections.Generic;
using System.Linq;

struct Element
{
    public char Op;
    public int Number;
    public Element(char op, int number)
    {
        Op = op;
        Number = number;
    }
}

public class Solution {
    public int Calculate(string s) {
        var stack = new Stack<Element>();
        var readingNumber = false;
        var number = 0;
        var op = '+';
        foreach (var ch in ((IEnumerable<char>)s).Concat(Enumerable.Repeat('+', 1)))
        {
            if (ch >= '0' && ch <= '9')
            {
                if (!readingNumber)
                {
                    readingNumber = true;
                    number = 0;
                }
                number = (number * 10) + (ch - '0');
            }
            else if (ch != ' ')
            {
                readingNumber = false;
                if (op == '+' || op == '-')
                {
                    if (stack.Count == 2)
                    {
                        var prev = stack.Pop();
                        var first = stack.Pop();
                        if (prev.Op == '+')
                        {
                            stack.Push(new Element(first.Op, first.Number + prev.Number));
                        }
                        else // '-'
                        {
                            stack.Push(new Element(first.Op, first.Number - prev.Number));
                        }
                    }
                    stack.Push(new Element(op, number));
                }
                else
                {
                    var prev = stack.Pop();
                    if (op == '*')
                    {
                        stack.Push(new Element(prev.Op, prev.Number * number));
                    }
                    else // '/'
                    {
                        stack.Push(new Element(prev.Op, prev.Number / number));
                    }
                }
                op = ch; 
            }
        }
        
        if (stack.Count == 2)
        {
            var second = stack.Pop();
            var first = stack.Pop();
            if (second.Op == '+')
            {
                stack.Push(new Element(first.Op, first.Number + second.Number));
            }
            else // '-'
            {
                stack.Push(new Element(first.Op, first.Number - second.Number));
            }
        }
        
        return stack.Peek().Number;
    }
}