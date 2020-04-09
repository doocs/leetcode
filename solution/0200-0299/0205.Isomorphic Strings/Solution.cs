using System.Collections.Generic;

public class Solution {
    public bool IsIsomorphic(string s, string t) {
        if (s.Length != t.Length) return false;
        var dict1 = new Dictionary<char, char>();
        var dict2 = new Dictionary<char, char>();
        for (var i = 0; i < s.Length; ++i)
        {
            char mapping1;
            char mapping2;
            var found1 = dict1.TryGetValue(s[i], out mapping1);
            var found2 = dict2.TryGetValue(t[i], out mapping2);
            if (found1 ^ found2) return false;
            if (!found1)
            {
                dict1.Add(s[i], t[i]);
                dict2.Add(t[i], s[i]);
            }
            else if (mapping1 != t[i] || mapping2 != s[i])
            {
                return false;
            }
        }
        return true;
    }
}