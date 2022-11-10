class Solution {
    public int eatenApples(int[] apples, int[] days) {
        PriorityQueue<int[]> q = new PriorityQueue<>(Comparator.comparingInt(a -> a[0]));
        int n = days.length;
        int ans = 0, i = 0;
        while (i < n || !q.isEmpty()) {
            if (i < n && apples[i] > 0) {
                q.offer(new int[] {i + days[i] - 1, apples[i]});
            }
            while (!q.isEmpty() && q.peek()[0] < i) {
                q.poll();
            }
            if (!q.isEmpty()) {
                var p = q.poll();
                ++ans;
                if (--p[1] > 0 && p[0] > i) {
                    q.offer(p);
                }
            }
            ++i;
        }
        return ans;
    }
}