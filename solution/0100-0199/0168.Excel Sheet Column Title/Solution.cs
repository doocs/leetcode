using System.Collections.Generic;
using System.Linq;
using System.Text;

public class Solution {
    public string ConvertToTitle(int n) {
        var list = new List<char>();
        while (n > 0)
        {
            var x = n % 26;
            if (x == 0) x = 26;
            list.Add((char)('A' + x - 1));
            n = (n - x) / 26;
        }
        var sb = new StringBuilder();
        for (var i = list.Count - 1; i >= 0; --i)
        {
            sb.Append(list[i]);
        }
        return sb.ToString();
    }
}