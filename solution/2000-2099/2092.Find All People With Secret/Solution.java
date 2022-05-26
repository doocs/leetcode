class Solution {
    public List<Integer> findAllPeople(int n, int[][] meetings, int firstPerson) {
        boolean[] vis = new boolean[n];
        vis[0] = true;
        vis[firstPerson] = true;
        int m = meetings.length;
        Arrays.sort(meetings, Comparator.comparingInt(a -> a[2]));
        for (int i = 0; i < m;) {
            int j = i;
            for (; j + 1 < m && meetings[j + 1][2] == meetings[i][2]; ++j);
            Map<Integer, List<Integer>> g = new HashMap<>();
            Set<Integer> s = new HashSet<>();
            for (int k = i; k <= j; ++k) {
                int x = meetings[k][0], y = meetings[k][1];
                g.computeIfAbsent(x, key -> new ArrayList<>()).add(y);
                g.computeIfAbsent(y, key -> new ArrayList<>()).add(x);
                s.add(x);
                s.add(y);
            }
            Deque<Integer> q = new ArrayDeque<>();
            for (int u : s) {
                if (vis[u]) {
                    q.offer(u);
                }
            }
            while (!q.isEmpty()) {
                int u = q.poll();
                for (int v : g.getOrDefault(u, Collections.emptyList())) {
                    if (!vis[v]) {
                        vis[v] = true;
                        q.offer(v);
                    }
                }
            }
            i = j + 1;
        }
        List<Integer> ans = new ArrayList<>();
        for (int i = 0; i < n; ++i) {
            if (vis[i]) {
                ans.add(i);
            }
        }
        return ans;
    }
}