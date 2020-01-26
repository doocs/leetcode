using System.Collections.Generic;

public class Solution {
    public int Calculate(string s) {
        var numbers = new Stack<int>();
        var symbols = new Stack<char>();
        int number = -1;
        for (var i = 0; i <= s.Length; ++i)
        {
            var ch = i < s.Length ? s[i] : ' ';
            if (char.IsDigit(ch))
            {
                if (number == -1) number = 0;
                number = number * 10 + ch - '0';
            }
            else
            {
                if (number != -1)
                {
                    numbers.Push(number);
                    while (symbols.Count > 0 && symbols.Peek() != '(')
                    {
                        var symbol = symbols.Pop();
                        if (symbol == '+')
                        {
                            numbers.Push(numbers.Pop() + numbers.Pop());
                        }
                        else
                        {
                            numbers.Push(-(numbers.Pop() - numbers.Pop()));
                        }
                    }
                    number = -1;
                }
                if (char.IsWhiteSpace(ch)) continue;

                if (ch == ')')
                {
                    symbols.Pop();
                    while (symbols.Count > 0 && symbols.Peek() != '(')
                    {
                        var symbol = symbols.Pop();
                        if (symbol == '+')
                        {
                            numbers.Push(numbers.Pop() + numbers.Pop());
                        }
                        else
                        {
                            numbers.Push(-(numbers.Pop() - numbers.Pop()));
                        }
                    }
                }
                else
                {
                    symbols.Push(ch);
                }
            }
        }

        return numbers.Pop();
    }
}