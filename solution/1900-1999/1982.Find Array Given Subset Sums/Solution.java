class Solution {
    public int[] recoverArray(int n, int[] sums) {
        int m = 1 << 30;
        for (int x : sums) {
            m = Math.min(m, x);
        }
        m = -m;
        TreeMap<Integer, Integer> tm = new TreeMap<>();
        for (int x : sums) {
            tm.merge(x + m, 1, Integer::sum);
        }
        int[] ans = new int[n];
        if (tm.merge(0, -1, Integer::sum) == 0) {
            tm.remove(0);
        }
        ans[0] = tm.firstKey();
        for (int i = 1; i < n; ++i) {
            for (int j = 0; j < 1 << i; ++j) {
                if ((j >> (i - 1) & 1) == 1) {
                    int s = 0;
                    for (int k = 0; k < i; ++k) {
                        if (((j >> k) & 1) == 1) {
                            s += ans[k];
                        }
                    }
                    if (tm.merge(s, -1, Integer::sum) == 0) {
                        tm.remove(s);
                    }
                }
            }
            ans[i] = tm.firstKey();
        }
        for (int i = 0; i < 1 << n; ++i) {
            int s = 0;
            for (int j = 0; j < n; ++j) {
                if (((i >> j) & 1) == 1) {
                    s += ans[j];
                }
            }
            if (s == m) {
                for (int j = 0; j < n; ++j) {
                    if (((i >> j) & 1) == 1) {
                        ans[j] *= -1;
                    }
                }
                break;
            }
        }
        return ans;
    }
}