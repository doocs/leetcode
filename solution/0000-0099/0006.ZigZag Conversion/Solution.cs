using System.Collections.Generic;
using System.Linq;

public class Solution {
    public string Convert(string s, int numRows) {
        if (numRows == 1) return s;
        if (numRows > s.Length) numRows = s.Length;
        var rows = new List<char>[numRows];
        var i = 0;
        var j = 0;
        var down = true;
        while (i < s.Length)
        {
            if (rows[j] == null)
            {
                rows[j] = new List<char>();
            }
            rows[j].Add(s[i]);
            j = j + (down ? 1 : -1);
            if (j == numRows || j < 0)
            {
                down = !down;
                j = j + (down ? 2 : -2);
            }
            ++i;
        }
        return new string(rows.SelectMany(row => row).ToArray());
    }
}