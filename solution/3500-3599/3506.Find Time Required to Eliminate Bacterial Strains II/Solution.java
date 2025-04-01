class Solution {
    public long minEliminationTime(int[] timeReq, int splitTime) {
        PriorityQueue<Long> q = new PriorityQueue<>();
        for (int x : timeReq) {
            q.offer((long) x);
        }
        while (q.size() > 1) {
            q.poll();
            q.offer(q.poll() + splitTime);
        }
        return q.poll();
    }
}