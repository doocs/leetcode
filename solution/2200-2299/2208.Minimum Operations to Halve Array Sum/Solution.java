class Solution {
    public int halveArray(int[] nums) {
        long s = 0;
        PriorityQueue<Double> q = new PriorityQueue<>(Collections.reverseOrder());
        for (int v : nums) {
            q.offer(v * 1.0);
            s += v;
        }
        double d = s / 2.0;
        int ans = 0;
        while (d > 0) {
            double t = q.poll();
            d -= t / 2.0;
            q.offer(t / 2.0);
            ++ans;
        }
        return ans;
    }
}