class Solution {
    public int[] movesToStamp(String stamp, String target) {
        int m = stamp.length(), n = target.length();
        int[] indeg = new int[n - m + 1];
        Arrays.fill(indeg, m);
        List<Integer>[] g = new List[n];
        Arrays.setAll(g, i -> new ArrayList<>());
        Deque<Integer> q = new ArrayDeque<>();
        for (int i = 0; i < n - m + 1; ++i) {
            for (int j = 0; j < m; ++j) {
                if (target.charAt(i + j) == stamp.charAt(j)) {
                    if (--indeg[i] == 0) {
                        q.offer(i);
                    }
                } else {
                    g[i + j].add(i);
                }
            }
        }
        List<Integer> ans = new ArrayList<>();
        boolean[] vis = new boolean[n];
        while (!q.isEmpty()) {
            int i = q.poll();
            ans.add(i);
            for (int j = 0; j < m; ++j) {
                if (!vis[i + j]) {
                    vis[i + j] = true;
                    for (int k : g[i + j]) {
                        if (--indeg[k] == 0) {
                            q.offer(k);
                        }
                    }
                }
            }
        }
        for (int i = 0; i < n; ++i) {
            if (!vis[i]) {
                return new int[0];
            }
        }
        Collections.reverse(ans);
        return ans.stream().mapToInt(Integer::intValue).toArray();
    }
}