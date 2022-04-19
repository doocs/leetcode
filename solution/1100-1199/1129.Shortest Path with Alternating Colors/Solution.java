class Solution {
    public int[] shortestAlternatingPaths(int n, int[][] redEdges, int[][] blueEdges) {
        List<Integer>[] red = get(n, redEdges);
        List<Integer>[] blue = get(n, blueEdges);
        boolean[] visBlue = new boolean[n];
        boolean[] visRed = new boolean[n];
        Deque<int[]> q = new ArrayDeque<>();
        q.offer(new int[]{0, 1});
        q.offer(new int[]{0, 0});
        int d = -1;
        int[] ans = new int[n];
        Arrays.fill(ans, -1);
        while (!q.isEmpty()) {
            ++d;
            for (int t = q.size(); t > 0; --t) {
                int[] p = q.poll();
                int i = p[0];
                boolean b = p[1] == 1;
                if (ans[i] == -1 || ans[i] > d) {
                    ans[i] = d;
                }
                boolean[] vis = b ? visBlue : visRed;
                vis[i] = true;
                List<Integer> ne = b ? red[i] : blue[i];
                boolean[] v = b ? visRed : visBlue;
                for (int j : ne) {
                    if (!v[j]) {
                        v[j] = true;
                        q.offer(new int[]{j, 1 - p[1]});
                    }
                }
            }
        }
        return ans;
    }

    private List<Integer>[] get(int n, int[][] edges) {
        List<Integer>[] res = new List[n];
        for (int i = 0; i < n; ++i) {
            res[i] = new ArrayList<>();
        }
        for (int[] e : edges) {
            res[e[0]].add(e[1]);
        }
        return res;
    }
}