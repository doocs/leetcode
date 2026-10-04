class Solution {
    public List<Integer> minimumFlips(int n, int[][] edges, String start, String target) {
        List<int[]>[] g = new List[n];
        Arrays.setAll(g, k -> new ArrayList<>());
        for (int i = 0; i < n - 1; ++i) {
            int a = edges[i][0], b = edges[i][1];
            g[a].add(new int[] {b, i});
            g[b].add(new int[] {a, i});
        }
        List<Integer> ans = new ArrayList<>();
        boolean[] need = new boolean[n];
        Deque<int[]> stk = new ArrayDeque<>();
        stk.push(new int[] {0, -1, 0});
        while (!stk.isEmpty()) {
            int[] cur = stk.pop();
            int a = cur[0], fa = cur[1], state = cur[2];
            if (state == 0) {
                stk.push(new int[] {a, fa, 1});
                for (var e : g[a]) {
                    int b = e[0];
                    if (b != fa) {
                        stk.push(new int[] {b, a, 0});
                    }
                }
            } else {
                boolean rev = start.charAt(a) != target.charAt(a);
                for (var e : g[a]) {
                    int b = e[0], i = e[1];
                    if (b != fa && need[b]) {
                        ans.add(i);
                        rev = !rev;
                    }
                }
                need[a] = rev;
            }
        }
        if (need[0]) {
            return List.of(-1);
        }
        Collections.sort(ans);
        return ans;
    }
}
