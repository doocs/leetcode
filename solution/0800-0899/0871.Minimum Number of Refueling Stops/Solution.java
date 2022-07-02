class Solution {
    public int minRefuelStops(int target, int startFuel, int[][] stations) {
        PriorityQueue<Integer> q = new PriorityQueue<>((a, b) -> b - a);
        int n = stations.length;
        int prev = 0, ans = 0;
        for (int i = 0; i < n + 1; ++i) {
            int d = (i < n ? stations[i][0] : target) - prev;
            startFuel -= d;
            while (startFuel < 0 && !q.isEmpty()) {
                startFuel += q.poll();
                ++ans;
            }
            if (startFuel < 0) {
                return -1;
            }
            if (i < n) {
                q.offer(stations[i][1]);
                prev = stations[i][0];
            }
        }
        return ans;
    }
}