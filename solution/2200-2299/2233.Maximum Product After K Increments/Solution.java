class Solution {
    public int maximumProduct(int[] nums, int k) {
        PriorityQueue<Integer> pq = new PriorityQueue<>();
        for (int x : nums) {
            pq.offer(x);
        }
        while (k-- > 0) {
            pq.offer(pq.poll() + 1);
        }
        final int mod = (int) 1e9 + 7;
        long ans = 1;
        for (int x : pq) {
            ans = (ans * x) % mod;
        }
        return (int) ans;
    }
}
