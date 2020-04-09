using System;
using System.Collections.Generic;

public class Solution {
    public string AddBinary(string a, string b) {
        var list = new List<char>(Math.Max(a.Length, b.Length) + 1);
        var i = a.Length - 1;
        var j = b.Length - 1;
        var carry = 0;
        while (i >= 0 || j >= 0)
        {
            if (i >= 0)
            {
                carry += a[i] - '0';
            }
            if (j >= 0)
            {
                carry += b[j] - '0';
            }
            list.Add((char)((carry % 2) + '0'));
            carry /= 2;
            --i;
            --j;
        }
        if (carry > 0) list.Add((char) (carry + '0'));
        list.Reverse();
        return new string(list.ToArray());
    }
}