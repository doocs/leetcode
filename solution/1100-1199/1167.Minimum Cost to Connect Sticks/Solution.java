class Solution {
    public int connectSticks(int[] sticks) {
        PriorityQueue<Integer> pq = new PriorityQueue<>();
        for (int s : sticks) {
            pq.offer(s);
        }
        int res = 0;
        while (pq.size() > 1) {
            int val = pq.poll() + pq.poll();
            res += val;
            pq.offer(val);
        }
        return res;
    }
}