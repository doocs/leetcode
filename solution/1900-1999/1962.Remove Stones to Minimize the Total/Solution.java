class Solution {
    public int minStoneSum(int[] piles, int k) {
        PriorityQueue<Integer> pq = new PriorityQueue<>((a, b) -> b - a);
        for (int x : piles) {
            pq.offer(x);
        }
        while (k-- > 0) {
            int x = pq.poll();
            pq.offer(x - x / 2);
        }
        int ans = 0;
        while (!pq.isEmpty()) {
            ans += pq.poll();
        }
        return ans;
    }
}