class Solution {
    public int lastStoneWeight(int[] stones) {
        PriorityQueue<Integer> q = new PriorityQueue<>((a, b) -> b - a);
        for (int x : stones) {
            q.offer(x);
        }
        while (q.size() > 1) {
            int y = q.poll();
            int x = q.poll();
            if (x != y) {
                q.offer(y - x);
            }
        }
        return q.isEmpty() ? 0 : q.poll();
    }
}