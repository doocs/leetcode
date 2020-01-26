using System.Collections.Generic;

public class Solution {
    public IList<int> DiffWaysToCompute(string input) {
        var values = new List<int>();
        var operators = new List<char>();
        var sum = 0;
        foreach (var ch in input)
        {
            if (ch == '+' || ch == '-' || ch == '*')
            {
                values.Add(sum);
                operators.Add(ch);
                sum = 0;
            }
            else
            {
                sum = sum * 10 + ch - '0';
            }
        }
        values.Add(sum);

        var f = new List<int>[values.Count, values.Count];
        for (var i = 0; i < values.Count; ++i)
        {
            f[i, i] = new List<int> { values[i] };
        }

        for (var diff = 1; diff < values.Count; ++diff)
        {
            for (var left = 0; left + diff < values.Count; ++left)
            {
                var right = left + diff;
                f[left, right] = new List<int>();
                for (var i = left; i < right; ++i)
                {
                    foreach (var leftValue in f[left, i])
                    {
                        foreach (var rightValue in f[i + 1, right])
                        {
                            switch (operators[i])
                            {
                                case '+':
                                    f[left, right].Add(leftValue + rightValue);
                                    break;
                                case '-':
                                    f[left, right].Add(leftValue - rightValue);
                                    break;
                                case '*':
                                    f[left, right].Add(leftValue * rightValue);
                                    break;
                            }
                        }
                    }
                }
            }
        }

        return f[0, values.Count - 1];
    }
}