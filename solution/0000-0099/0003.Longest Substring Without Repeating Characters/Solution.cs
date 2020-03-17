using System.Collections.Generic;

public class Solution {
    public int LengthOfLongestSubstring(string s) {
        var hashSet = new HashSet<char>();
        var maxLength = 0;
        int i = 0, j = 0;
        while (i < s.Length)
        {
            while (hashSet.Contains(s[i]))
            {
                hashSet.Remove(s[j++]);
            }
            hashSet.Add(s[i++]);
            if (i - j > maxLength)
            {
                maxLength = i - j;
            }
        }
        return maxLength;
    }
}