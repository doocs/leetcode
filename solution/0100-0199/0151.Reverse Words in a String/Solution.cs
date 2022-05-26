using System.Text;
using System.Collections.Generic;
using System.Linq;

public class Solution {
    public string ReverseWords(string s) {
        var sb = new StringBuilder();
        var wordList = new List<string>();
        foreach (var ch in s)
        {
            if (char.IsWhiteSpace(ch))
            {
                if (sb.Length > 0)
                {
                    wordList.Add(sb.ToString());
                    sb.Clear();
                }
            }
            else
            {
                sb.Append(ch);
            }
        }
        if (sb.Length > 0)
        {
            wordList.Add(sb.ToString());
            sb.Clear();
        }
        
        foreach (var word in ((IEnumerable<string>)wordList).Reverse())
        {
            if (sb.Length > 0)
            {
                sb.Append(' ');
            }
            sb.Append(word);
        }

        return sb.ToString();
    }
}