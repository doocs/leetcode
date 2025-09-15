class Solution {
    public int minArrivalsToDiscard(int[] arrivals, int w, int m) {
        Map<Integer, Integer> cnt = new HashMap<>();
        int n = arrivals.length;
        int[] marked = new int[n];
        int ans = 0;
        for (int i = 0; i < n; i++) {
            int x = arrivals[i];
            if (i >= w) {
                int prev = arrivals[i - w];
                cnt.merge(prev, -marked[i - w], Integer::sum);
            }
            if (cnt.getOrDefault(x, 0) >= m) {
                ans++;
            } else {
                marked[i] = 1;
                cnt.merge(x, 1, Integer::sum);
            }
        }
        return ans;
    }
}
