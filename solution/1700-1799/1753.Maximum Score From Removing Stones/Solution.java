class Solution {
    public int maximumScore(int a, int b, int c) {
        PriorityQueue<Integer> pq = new PriorityQueue<>((x, y) -> y - x);
        pq.offer(a);
        pq.offer(b);
        pq.offer(c);
        int ans = 0;
        while (true) {
            int x = pq.poll(), y = pq.poll();
            if (y == 0) {
                break;
            }
            pq.offer(x - 1);
            pq.offer(y - 1);
            ++ans;
        }
        return ans;
    }
}