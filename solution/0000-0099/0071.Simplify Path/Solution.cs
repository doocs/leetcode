using System.Collections.Generic;
using System.Linq;
using System.Text;

public class Solution {
    public string SimplifyPath(string path) {
        var stack = new Stack<string>();
        var sb = new StringBuilder();
        foreach (var ch in ((IEnumerable<char>)path).Concat(Enumerable.Repeat('/', 1)))
        {
            if (ch == '/')
            {
                if (sb.Length > 0)
                {
                    var folder = sb.ToString();
                    sb.Clear();
                    switch (folder)
                    {
                        case ".":
                            break;
                        case "..":
                            if (stack.Any())
                            {
                                stack.Pop();
                            }
                            break;
                        default:
                            stack.Push(folder);
                            break;
                    }
                }
            }
            else
            {
                sb.Append(ch);
            }
        }
        
        if (stack.Count == 0)
        {
            sb.Append('/');
        }
        foreach (var folder in ((IEnumerable<string>)stack.ToList()).Reverse())
        {
            sb.Append('/');
            sb.Append(folder);
        }
        return sb.ToString();
    }
}