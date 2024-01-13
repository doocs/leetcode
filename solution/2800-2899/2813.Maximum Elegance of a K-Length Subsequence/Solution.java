class Solution {
    public long findMaximumElegance(int[][] items, int k) {
        Arrays.sort(items, (a, b) -> b[0] - a[0]);
        int n = items.length;
        long tot = 0;
        Set<Integer> vis = new HashSet<>();
        Deque<Integer> dup = new ArrayDeque<>();
        for (int i = 0; i < k; ++i) {
            int p = items[i][0], c = items[i][1];
            tot += p;
            if (!vis.add(c)) {
                dup.push(p);
            }
        }
        long ans = tot + (long) vis.size() * vis.size();
        for (int i = k; i < n; ++i) {
            int p = items[i][0], c = items[i][1];
            if (vis.contains(c) || dup.isEmpty()) {
                continue;
            }
            vis.add(c);
            tot += p - dup.pop();
            ans = Math.max(ans, tot + (long) vis.size() * vis.size());
        }
        return ans;
    }
}