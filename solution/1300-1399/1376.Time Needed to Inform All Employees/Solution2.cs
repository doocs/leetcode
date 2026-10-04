public class Solution {
    public int NumOfMinutes(int n, int headID, int[] manager, int[] informTime) {
        List<int>[] g = new List<int>[n];
        for (int i = 0; i < n; ++i) {
            g[i] = new List<int>();
        }
        for (int i = 0; i < n; ++i) {
            if (manager[i] != -1) {
                g[manager[i]].Add(i);
            }
        }
        int[] time = new int[n];
        Stack<int[]> stk = new Stack<int[]>();
        stk.Push(new int[] { headID, 0 });
        while (stk.Count > 0) {
            int[] cur = stk.Pop();
            int i = cur[0], state = cur[1];
            if (state == 0) {
                stk.Push(new int[] { i, 1 });
                foreach (int j in g[i]) {
                    stk.Push(new int[] { j, 0 });
                }
            } else {
                int ans = 0;
                foreach (int j in g[i]) {
                    ans = Math.Max(ans, time[j] + informTime[i]);
                }
                time[i] = ans;
            }
        }
        return time[headID];
    }
}
