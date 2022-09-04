class Solution {
    public int maximumRobots(int[] chargeTimes, int[] runningCosts, long budget) {
        Deque<Integer> q = new ArrayDeque<>();
        int n = chargeTimes.length;
        long s = 0;
        int j = 0;
        int ans = 0;
        for (int i = 0; i < n; ++i) {
            int a = chargeTimes[i], b = runningCosts[i];
            while (!q.isEmpty() && chargeTimes[q.getLast()] <= a) {
                q.pollLast();
            }
            q.offer(i);
            s += b;
            while (!q.isEmpty() && chargeTimes[q.getFirst()] + (i - j + 1) * s > budget) {
                if (q.getFirst() == j) {
                    q.pollFirst();
                }
                s -= runningCosts[j++];
            }
            ans = Math.max(ans, i - j + 1);
        }
        return ans;
    }
}