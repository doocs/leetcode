public class Solution {
    public int MinimumIncompatibility(int[] nums, int k) {
        int n = nums.Length;
        int m = n / k;
        int[] g = new int[1 << n];
        Array.Fill(g, -1);
        for (int i = 1; i < 1 << n; ++i) {
            if (bitCount(i) != m) {
                continue;
            }
            HashSet<int> s = new();
            int mi = 20, mx = 0;
            for (int j = 0; j < n; ++j) {
                if ((i >> j & 1) == 1) {
                    if (s.Contains(nums[j])) {
                        break;
                    }
                    s.Add(nums[j]);
                    mi = Math.Min(mi, nums[j]);
                    mx = Math.Max(mx, nums[j]);
                }
            }
            if (s.Count == m) {
                g[i] = mx - mi;
            }
        }
        int[] f = new int[1 << n];
        int inf = 1 << 30;
        Array.Fill(f, inf);
        f[0] = 0;
        for (int i = 0; i < 1 << n; ++i) {
            if (f[i] == inf) {
                continue;
            }
            HashSet<int> s = new();
            int mask = 0;
            for (int j = 0; j < n; ++j) {
                if ((i >> j & 1) == 0 && !s.Contains(nums[j])) {
                    s.Add(nums[j]);
                    mask |= 1 << j;
                }
            }
            if (s.Count < m) {
                continue;
            }
            for (int j = mask; j > 0; j = (j - 1) & mask) {
                if (g[j] != -1) {
                    f[i | j] = Math.Min(f[i | j], f[i] + g[j]);
                }
            }
        }
        return f[(1 << n) - 1] == inf ? -1 : f[(1 << n) - 1];
    }

    private int bitCount(int x) {
        int cnt = 0;
        while (x > 0) {
            x &= x - 1;
            ++cnt;
        }
        return cnt;
    }
}
