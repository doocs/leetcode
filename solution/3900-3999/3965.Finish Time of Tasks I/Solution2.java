class Solution {
    public long finishTime(int n, int[][] edges, int[] baseTime) {
        List<Integer>[] g = new List[n];
        Arrays.setAll(g, k -> new ArrayList<>());
        for (int[] e : edges) {
            g[e[0]].add(e[1]);
        }
        long[] fin = new long[n];
        Deque<int[]> stk = new ArrayDeque<>();
        stk.push(new int[] {0, 0});
        while (!stk.isEmpty()) {
            int[] cur = stk.pop();
            int i = cur[0], state = cur[1];
            if (state == 0) {
                if (g[i].isEmpty()) {
                    fin[i] = baseTime[i];
                } else {
                    stk.push(new int[] {i, 1});
                    for (int j : g[i]) {
                        stk.push(new int[] {j, 0});
                    }
                }
            } else {
                long earliest = Long.MAX_VALUE;
                long latest = Long.MIN_VALUE;
                for (int j : g[i]) {
                    earliest = Math.min(earliest, fin[j]);
                    latest = Math.max(latest, fin[j]);
                }
                long ownDuration = (latest - earliest) + baseTime[i];
                fin[i] = latest + ownDuration;
            }
        }
        return fin[0];
    }
}
