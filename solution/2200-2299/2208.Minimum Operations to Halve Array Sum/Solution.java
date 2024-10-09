class Solution {
    public int halveArray(int[] nums) {
        PriorityQueue<Double> pq = new PriorityQueue<>(Collections.reverseOrder());
        double s = 0;
        for (int x : nums) {
            s += x;
            pq.offer((double) x);
        }
        s /= 2.0;
        int ans = 0;
        while (s > 0) {
            double t = pq.poll() / 2.0;
            s -= t;
            pq.offer(t);
            ++ans;
        }
        return ans;
    }
}
