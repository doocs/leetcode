class Solution {
    private static final int MOD = 1_000_000_007;

    public int xorAfterQueries(int[] nums, int[][] queries) {
        int n = nums.length;
        int B = (int) Math.sqrt(n) + 1;
        List<int[]>[][] events = new List[B + 1][];
        for (int k = 1; k <= B; ++k) {
            events[k] = new List[k];
            for (int res = 0; res < k; ++res) {
                events[k][res] = new ArrayList<>();
            }
        }
        for (int[] q : queries) {
            int l = q[0], r = q[1], k = q[2], v = q[3];
            if (k > B) {
                for (int idx = l; idx <= r; idx += k) {
                    nums[idx] = (int) ((long) nums[idx] * v % MOD);
                }
            } else {
                int res = l % k;
                int t1 = (l - res) / k;
                int t2 = (r - res) / k;
                events[k][res].add(new int[] {t1, v});
                if (t2 + 1 <= (n - 1 - res) / k) {
                    events[k][res].add(new int[] {t2 + 1, (int) qpow(v, MOD - 2)});
                }
            }
        }
        for (int k = 1; k <= B; ++k) {
            for (int res = 0; res < k; ++res) {
                List<int[]> ev = events[k][res];
                if (ev.isEmpty()) {
                    continue;
                }
                ev.sort(Comparator.comparingInt(a -> a[0]));
                List<int[]> comp = new ArrayList<>();
                for (int[] p : ev) {
                    if (!comp.isEmpty() && comp.get(comp.size() - 1)[0] == p[0]) {
                        int[] last = comp.get(comp.size() - 1);
                        last[1] = (int) ((long) last[1] * p[1] % MOD);
                    } else {
                        comp.add(new int[] {p[0], p[1]});
                    }
                }
                long cur = 1;
                int ptr = 0, t = 0;
                for (int idx = res; idx < n; idx += k, ++t) {
                    while (ptr < comp.size() && comp.get(ptr)[0] == t) {
                        cur = cur * comp.get(ptr)[1] % MOD;
                        ++ptr;
                    }
                    nums[idx] = (int) (nums[idx] * cur % MOD);
                }
            }
        }
        int xr = 0;
        for (int x : nums) {
            xr ^= x;
        }
        return xr;
    }

    private long qpow(long a, long n) {
        long ans = 1;
        for (; n > 0; n >>= 1) {
            if ((n & 1) == 1) {
                ans = ans * a % MOD;
            }
            a = a * a % MOD;
        }
        return ans;
    }
}
