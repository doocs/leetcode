using System;

public class Solution {
    private long[] s;
    private long[] d;
    private int n;

    public long MaxPower(int[] stations, int r, int k) {
        n = stations.Length;
        d = new long[n + 1];
        s = new long[n + 1];

        for (int i = 0; i < n; ++i) {
            int left = Math.Max(0, i - r);
            int right = Math.Min(i + r, n - 1);
            d[left] += stations[i];
            d[right + 1] -= stations[i];
        }

        s[0] = d[0];
        for (int i = 1; i < n + 1; ++i) {
            s[i] = s[i - 1] + d[i];
        }

        long leftBound = 0, rightBound = 1L << 40;
        while (leftBound < rightBound) {
            long mid = (leftBound + rightBound + 1) >> 1;
            if (Check(mid, r, k)) {
                leftBound = mid;
            } else {
                rightBound = mid - 1;
            }
        }

        return leftBound;
    }

    private bool Check(long x, int r, long k) {
        Array.Fill(d, 0L);
        long t = 0;

        for (int i = 0; i < n; ++i) {
            t += d[i];
            long dist = x - (s[i] + t);
            if (dist > 0) {
                if (k < dist) {
                    return false;
                }
                k -= dist;
                int j = Math.Min(i + r, n - 1);
                int left = Math.Max(0, j - r);
                int right = Math.Min(j + r, n - 1);
                d[left] += dist;
                d[right + 1] -= dist;
                t += dist;
            }
        }

        return true;
    }
}
