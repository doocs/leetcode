using System.Collections.Generic;

public class Solution {
    public IList<string> FindRepeatedDnaSequences(string s) {
        var once = new HashSet<int>();
        var moreThanOnce = new HashSet<int>();
        int bits = 0;
        for (var i = 0; i < s.Length; ++i)
        {
            bits <<= 2;
            switch (s[i])
            {
                case 'A':
                    break;
                case 'C':
                    bits |= 1;
                    break;
                case 'G':
                    bits |= 2;
                    break;
                case 'T':
                    bits |= 3;
                    break;
            }
            if (i >= 10)
            {
                bits &= 0xFFFFF;
            }
            if (i >= 9 && !once.Add(bits))
            {
                moreThanOnce.Add(bits);
            }
        }
        
        var results = new List<string>();
        foreach (var item in moreThanOnce)
        {
            var itemCopy = item;
            var charArray = new char[10];
            for (var i = 9; i >= 0; --i)
            {
                switch (itemCopy & 3)
                {
                    case 0:
                        charArray[i] = 'A';
                        break;
                    case 1:
                        charArray[i] = 'C';
                        break;
                    case 2:
                        charArray[i] = 'G';
                        break;
                    case 3:
                        charArray[i] = 'T';
                        break;
                }
                itemCopy >>= 2;
            }
            results.Add(new string(charArray));
        }
        return results;
    }
}