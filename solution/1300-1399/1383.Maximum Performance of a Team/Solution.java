class Solution {
    public int maxPerformance(int n, int[] speed, int[] efficiency, int k) {
        int[][] team = new int[n][2];
        for (int i = 0; i < n; ++i) {
            team[i] = new int[]{speed[i], efficiency[i]};
        }
        Arrays.sort(team, (a, b) -> b[1] - a[1]);
        PriorityQueue<Integer> q = new PriorityQueue<>((a, b) -> a - b);
        long t = 0;
        long ans = 0;
        int mod = (int) 1e9 + 7;
        for (int[] x : team) {
            int s = x[0], e = x[1];
            t += s;
            ans = Math.max(ans, t * e);
            q.offer(s);
            if (q.size() >= k) {
                t -= q.poll();
            }
        }
        return (int) (ans % mod);
    }
}