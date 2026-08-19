using System;
using System.Collections.Generic;

public class Solution {
    public int MaxNumberOfFamilies(int n, int[][] reservedSeats) {
        Dictionary<int, int> d = new Dictionary<int, int>();

        foreach (var e in reservedSeats) {
            int row = e[0];
            int col = e[1];
            int mask = 1 << (10 - col);

            if (d.ContainsKey(row)) {
                d[row] |= mask;
            } else {
                d[row] = mask;
            }
        }

        int[] masks = {
            0b0111100000,
            0b0000011110,
            0b0001111000
        };

        int ans = (n - d.Count) * 2;

        foreach (int value in d.Values) {
            int x = value;

            foreach (int mask in masks) {
                if ((x & mask) == 0) {
                    x |= mask;
                    ans++;
                }
            }
        }

        return ans;
    }
}