class Solution {
    public boolean possibleBipartition(int n, int[][] dislikes) {
        List<Integer>[] g = new List[n];
        Arrays.setAll(g, k -> new ArrayList<>());
        for (var e : dislikes) {
            int a = e[0] - 1, b = e[1] - 1;
            g[a].add(b);
            g[b].add(a);
        }
        int[] color = new int[n];
        for (int start = 0; start < n; ++start) {
            if (color[start] != 0) {
                continue;
            }
            color[start] = 1;
            Deque<Integer> stk = new ArrayDeque<>();
            stk.push(start);
            while (!stk.isEmpty()) {
                int i = stk.pop();
                for (int j : g[i]) {
                    if (color[j] == color[i]) {
                        return false;
                    }
                    if (color[j] == 0) {
                        color[j] = 3 - color[i];
                        stk.push(j);
                    }
                }
            }
        }
        return true;
    }
}
