class Solution {
    public int eatenApples(int[] apples, int[] days) {
        PriorityQueue<int[]> q = new PriorityQueue<>(Comparator.comparingInt(a -> a[0]));
        int ans = 0, i = 0, n = apples.length;
        while (i < n || !q.isEmpty()) {
            if (i < n && apples[i] > 0) {
                q.offer(new int[]{i + days[i] - 1, apples[i]});
            }
            while (!q.isEmpty() && q.peek()[0] < i) {
                q.poll();
            }
            if (!q.isEmpty()) {
                int[] t = q.poll();
                if (--t[1] > 0 && t[0] > i) {
                    q.offer(t);
                }
                ++ans;
            }
            ++i;
        }
        return ans;
    }
}