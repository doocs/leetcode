using System.Collections.Generic;

public class Solution {
    public int LongestValidParentheses(string s) {
        var result = 0;
        var baseCount = 0;
        var stack = new Stack<int>();
        foreach (var ch in s)
        {
            switch (ch)
            {
                case '(':
                    stack.Push(1);
                    break;
                case ')':
                    if (stack.Count > 0)
                    {
                        var count = stack.Pop() + 1;
                        if (stack.Count > 0)
                        {
                            count += stack.Pop();
                            stack.Push(count);
                            if (count - 1 > result) result = count - 1;
                        }
                        else
                        {
                            baseCount += count;
                            if (baseCount > result) result = baseCount;
                        }
                    }
                    else
                    {
                        baseCount = 0;
                    }
                    break;
            }
        }
        return result;
    }
}