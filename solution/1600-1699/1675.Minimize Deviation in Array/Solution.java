class Solution {
    public int minimumDeviation(int[] nums) {
        PriorityQueue<Integer> q = new PriorityQueue<>((a, b) -> b - a);
        int mi = Integer.MAX_VALUE;
        for (int v : nums) {
            if (v % 2 == 1) {
                v <<= 1;
            }
            q.offer(v);
            mi = Math.min(mi, v);
        }
        int ans = q.peek() - mi;
        while (q.peek() % 2 == 0) {
            int x = q.poll() / 2;
            q.offer(x);
            mi = Math.min(mi, x);
            ans = Math.min(ans, q.peek() - mi);
        }
        return ans;
    }
}