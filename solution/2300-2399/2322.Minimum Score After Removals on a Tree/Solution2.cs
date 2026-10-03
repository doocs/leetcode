public class Solution {
    public int MinimumScore(int[] nums, int[][] edges) {
        int n = nums.Length;
        List<int>[] g = new List<int>[n];
        for (int i = 0; i < n; i++) {
            g[i] = new List<int>();
        }
        foreach (var e in edges) {
            int a = e[0], b = e[1];
            g[a].Add(b);
            g[b].Add(a);
        }

        int s = 0;
        foreach (int x in nums) {
            s ^= x;
        }

        int ComponentXor(int root, int ban) {
            int[] sub = new int[n];
            var stk = new Stack<int[]>();
            stk.Push(new int[] { root, ban, 0 });
            while (stk.Count > 0) {
                int[] cur = stk.Pop();
                int i = cur[0], fa = cur[1], state = cur[2];
                if (state == 0) {
                    stk.Push(new int[] { i, fa, 1 });
                    foreach (int j in g[i]) {
                        if (j != fa) {
                            stk.Push(new int[] { j, i, 0 });
                        }
                    }
                } else {
                    int res = nums[i];
                    foreach (int j in g[i]) {
                        if (j != fa) {
                            res ^= sub[j];
                        }
                    }
                    sub[i] = res;
                }
            }
            return sub[root];
        }

        int Collect(int root, int ban, int s1) {
            int best = int.MaxValue;
            int[] sub = new int[n];
            var stk = new Stack<int[]>();
            stk.Push(new int[] { root, ban, 0 });
            while (stk.Count > 0) {
                int[] cur = stk.Pop();
                int i = cur[0], fa = cur[1], state = cur[2];
                if (state == 0) {
                    stk.Push(new int[] { i, fa, 1 });
                    foreach (int j in g[i]) {
                        if (j != fa) {
                            stk.Push(new int[] { j, i, 0 });
                        }
                    }
                } else {
                    int res = nums[i];
                    foreach (int j in g[i]) {
                        if (j != fa) {
                            int s2 = sub[j];
                            res ^= s2;
                            int mx = Math.Max(Math.Max(s ^ s1, s2), s1 ^ s2);
                            int mn = Math.Min(Math.Min(s ^ s1, s2), s1 ^ s2);
                            best = Math.Min(best, mx - mn);
                        }
                    }
                    sub[i] = res;
                }
            }
            return best;
        }

        int ans = int.MaxValue;
        for (int i = 0; i < n; ++i) {
            foreach (int j in g[i]) {
                int s1 = ComponentXor(i, j);
                ans = Math.Min(ans, Collect(i, j, s1));
            }
        }
        return ans;
    }
}
