using System.Collections.Generic;
using System.Linq;
using System.Text;

public class Solution {
    public IList<string> FullJustify(string[] words, int maxWidth) {
        var result = new List<string>();
        var buffer = new List<string>();
        var sb = new StringBuilder();
        var len = 0;
        
        for (var i = 0; i < words.Length; ++i)
        {
            var newLen = words[i].Length + (len == 0 ? 0 : len + 1);
            if (newLen <= maxWidth)
            {
                buffer.Add(words[i]);
                len = newLen;
            }
            else
            {
                if (buffer.Count == 1)
                {
                    sb.Append(buffer[0]);
                    sb.Append(' ', maxWidth - buffer[0].Length);
                }
                else
                {
                    var spaceCount = maxWidth - len + buffer.Count - 1;
                    for (var j = 0; j < buffer.Count - 1; ++j)
                    {
                        sb.Append(buffer[j]);
                        var spaceToAdd = (spaceCount - 1) / (buffer.Count - j - 1) + 1;
                        sb.Append(' ', spaceToAdd);
                        spaceCount -= spaceToAdd;
                    }
                    sb.Append(buffer.Last());
                }
                result.Add(sb.ToString());
                buffer.Clear();
                buffer.Add(words[i]);
                sb.Clear();
                len = words[i].Length;
            }
        }
        
        if (buffer.Count > 0)
        {
            for (var j = 0; j < buffer.Count; ++j)
            {
                if (sb.Length > 0)
                {
                    sb.Append(' ');
                }
                sb.Append(buffer[j]);
            }
            if (sb.Length < maxWidth)
            {
                sb.Append(' ', maxWidth - sb.Length);
            }
            result.Add(sb.ToString());
        }
        
        return result;
    }
}