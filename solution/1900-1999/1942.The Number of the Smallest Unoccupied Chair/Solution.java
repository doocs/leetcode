class Solution {
    public int smallestChair(int[][] times, int targetFriend) {
        int n = times.length;
        int[][] ts = new int[n][3];
        PriorityQueue<Integer> q = new PriorityQueue<>();
        PriorityQueue<int[]> busy = new PriorityQueue<>((a, b) -> a[0] - b[0]);
        for (int i = 0; i < n; ++i) {
            ts[i] = new int[] {times[i][0], times[i][1], i};
            q.offer(i);
        }
        Arrays.sort(ts, (a, b) -> a[0] - b[0]);
        for (int[] t : ts) {
            int a = t[0], b = t[1], i = t[2];
            while (!busy.isEmpty() && busy.peek()[0] <= a) {
                q.offer(busy.poll()[1]);
            }
            int c = q.poll();
            if (i == targetFriend) {
                return c;
            }
            busy.offer(new int[] {b, c});
        }
        return -1;
    }
}