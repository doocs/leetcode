class Solution {
    public long maxProfit(int[] workers, int[][] tasks) {
        Map<Integer, PriorityQueue<Integer>> d = new HashMap<>();
        for (var t : tasks) {
            int skill = t[0], profit = t[1];
            d.computeIfAbsent(skill, k -> new PriorityQueue<>((a, b) -> b - a)).offer(profit);
        }
        long ans = 0;
        for (int skill : workers) {
            if (d.containsKey(skill)) {
                var pq = d.get(skill);
                ans += pq.poll();
                if (pq.isEmpty()) {
                    d.remove(skill);
                }
            }
        }
        int mx = 0;
        for (var pq : d.values()) {
            mx = Math.max(mx, pq.peek());
        }
        ans += mx;
        return ans;
    }
}
