public class Solution {
    public int NumDecodings(string s) {
        if (s.Length == 0) return 0;
        
        var f0 = 1;
        var f1 = 1;
        var f2 = 1;
        for (var i = 0; i < s.Length; ++i)
        {
            f0 = f1;
            f1 = f2;
            f2 = 0;
            var two = i > 0 ? int.Parse(string.Format("{0}{1}", s[i - 1], s[i])) : 0;
            if (two >= 10 && two <= 26)
            {
               f2 += f0;  
            }
            if (s[i] != '0')
            {
                f2 += f1;
            }
        }
        return f2;
    }
}