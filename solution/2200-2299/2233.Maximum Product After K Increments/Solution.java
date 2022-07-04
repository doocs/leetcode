class Solution {
    private static final int MOD = (int) 1e9 + 7;

    public int maximumProduct(int[] nums, int k) {
        PriorityQueue<Integer> q = new PriorityQueue<>();
        for (int v : nums) {
            q.offer(v);
        }
        while (k-- > 0) {
            q.offer(q.poll() + 1);
        }
        long ans = 1;
        while (!q.isEmpty()) {
            ans = (ans * q.poll()) % MOD;
        }
        return (int) ans;
    }
}