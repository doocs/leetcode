using System.Text;
using System.Linq;

public class Solution {
    public string LongestCommonPrefix(string[] strs) {
        if (strs.Length == 0) return string.Empty;
        var sb = new StringBuilder();
        for (var i = 0; i < strs[0].Length; ++i)
        {
            var ch = strs[0][i];
            if (strs.All(str => str.Length > i && str[i] == ch))
            {
                sb.Append(ch);
            }
            else
            {
                break;
            }
        }
        return sb.ToString();
    }
}