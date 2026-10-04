public class Solution {
    public int CountHighestScoreNodes(int[] parents) {
        int n = parents.Length;
        List<int>[] g = new List<int>[n];
        for (int i = 0; i < n; ++i) {
            g[i] = new List<int>();
        }
        for (int i = 1; i < n; ++i) {
            g[parents[i]].Add(i);
        }
        int ans = 0;
        long mx = 0;
        int[] sz = new int[n];
        Stack<int[]> stk = new Stack<int[]>();
        stk.Push(new int[] { 0, -1, 0 });
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
                int cnt = 1;
                long score = 1;
                foreach (int j in g[i]) {
                    if (j != fa) {
                        int t = sz[j];
                        cnt += t;
                        score *= t;
                    }
                }
                if (n - cnt > 0) {
                    score *= n - cnt;
                }
                if (mx < score) {
                    mx = score;
                    ans = 1;
                } else if (mx == score) {
                    ++ans;
                }
                sz[i] = cnt;
            }
        }
        return ans;
    }
}
