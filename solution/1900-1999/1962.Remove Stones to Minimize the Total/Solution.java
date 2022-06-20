class Solution {
    public int minStoneSum(int[] piles, int k) {
        PriorityQueue<Integer> q = new PriorityQueue<>((a, b) -> (b - a));
        for (int p : piles) {
            q.offer(p);
        }
        while (k-- > 0) {
            int p = q.poll();
            q.offer((p + 1) >> 1);
        }
        int ans = 0;
        while (!q.isEmpty()) {
            ans += q.poll();
        }
        return ans;
    }
}