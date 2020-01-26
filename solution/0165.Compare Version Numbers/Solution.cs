using System;
using System.Linq;
using System.Collections.Generic;

public class Solution {
    public int CompareVersion(string version1, string version2) {
        var parts1 = version1.Split('.').Select(int.Parse).ToList();
        var parts2 = version2.Split('.').Select(int.Parse).ToList();
        var minLen = Math.Min(parts1.Count, parts2.Count);
        for (var i = 0; i < minLen; ++i)
        {
            if (parts1[i] < parts2[i])
            {
                return -1;
            }
            else if (parts1[i] > parts2[i])
            {
                return 1;
            }
        }
        if (parts1.Count > parts2.Count && parts1.Skip(minLen).Any(v => v != 0))
        {
            return 1;
        }
        if (parts2.Count > parts1.Count && parts2.Skip(minLen).Any(v => v != 0))
        {
            return -1;
        }
        return 0;
    }
}