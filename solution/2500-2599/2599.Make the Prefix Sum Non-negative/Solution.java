class Solution {
    public int makePrefSumNonNegative(int[] nums) {
        PriorityQueue<Integer> pq = new PriorityQueue<>();
        int ans = 0;
        long s = 0;
        for (int x : nums) {
            s += x;
            if (x < 0) {
                pq.offer(x);
            }
            while (s < 0) {
                s -= pq.poll();
                ++ans;
            }
        }
        return ans;
    }
}