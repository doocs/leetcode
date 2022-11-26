class Solution {
    public int numBusesToDestination(int[][] routes, int source, int target) {
        if (source == target) {
            return 0;
        }
        int n = routes.length;
        Set<Integer>[] s = new Set[n];
        List<Integer>[] g = new List[n];
        Arrays.setAll(s, k -> new HashSet<>());
        Arrays.setAll(g, k -> new ArrayList<>());
        Map<Integer, List<Integer>> d = new HashMap<>();
        for (int i = 0; i < n; ++i) {
            for (int v : routes[i]) {
                s[i].add(v);
                d.computeIfAbsent(v, k -> new ArrayList<>()).add(i);
            }
        }
        for (var ids : d.values()) {
            int m = ids.size();
            for (int i = 0; i < m; ++i) {
                for (int j = i + 1; j < m; ++j) {
                    int a = ids.get(i), b = ids.get(j);
                    g[a].add(b);
                    g[b].add(a);
                }
            }
        }
        Deque<Integer> q = new ArrayDeque<>();
        Set<Integer> vis = new HashSet<>();
        int ans = 1;
        for (int v : d.get(source)) {
            q.offer(v);
            vis.add(v);
        }
        while (!q.isEmpty()) {
            for (int k = q.size(); k > 0; --k) {
                int i = q.pollFirst();
                if (s[i].contains(target)) {
                    return ans;
                }
                for (int j : g[i]) {
                    if (!vis.contains(j)) {
                        vis.add(j);
                        q.offer(j);
                    }
                }
            }
            ++ans;
        }
        return -1;
    }
}