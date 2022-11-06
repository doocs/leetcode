class Solution {
    public long totalCost(int[] costs, int k, int candidates) {
        PriorityQueue<int[]> q = new PriorityQueue<>((a, b) -> {
            if (a[0] == b[0]) {
                return a[1] - b[1];
            }
            return a[0] - b[0];
        });
        int n = costs.length;
        int i = candidates - 1, j = n - candidates;
        for (int h = 0; h < candidates; ++h) {
            q.offer(new int[] {costs[h], h});
        }
        for (int h = n - candidates; h < n; ++h) {
            if (h > i) {
                q.offer(new int[] {costs[h], h});
            }
        }
        long ans = 0;
        while (k-- > 0) {
            var e = q.poll();
            int c = e[0], x = e[1];
            ans += c;
            if (x <= i) {
                if (++i < j) {
                    q.offer(new int[] {costs[i], i});
                }
            }
            if (x >= j) {
                if (--j > i) {
                    q.offer(new int[] {costs[j], j});
                }
            }
        }
        return ans;
    }
}