class Solution {
    public int maximumRobots(int[] chargeTimes, int[] runningCosts, long budget) {
        Deque<Integer> q = new ArrayDeque<>();
        int n = chargeTimes.length;
        int ans = 0;
        long s = 0;
        for (int l = 0, r = 0; r < n; ++r) {
            s += runningCosts[r];
            while (!q.isEmpty() && chargeTimes[q.peekLast()] <= chargeTimes[r]) {
                q.pollLast();
            }
            q.offerLast(r);
            while (!q.isEmpty() && (r - l + 1) * s + chargeTimes[q.peekFirst()] > budget) {
                if (q.peekFirst() == l) {
                    q.pollFirst();
                }
                s -= runningCosts[l++];
            }
            ans = Math.max(ans, r - l + 1);
        }
        return ans;
    }
}
