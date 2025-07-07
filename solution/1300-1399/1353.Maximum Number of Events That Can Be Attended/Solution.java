class Solution {
    public int maxEvents(int[][] events) {
        Map<Integer, List<Integer>> g = new HashMap<>();
        int l = Integer.MAX_VALUE, r = 0;
        for (int[] event : events) {
            int s = event[0], e = event[1];
            g.computeIfAbsent(s, k -> new ArrayList<>()).add(e);
            l = Math.min(l, s);
            r = Math.max(r, e);
        }
        PriorityQueue<Integer> pq = new PriorityQueue<>();
        int ans = 0;
        for (int s = l; s <= r; s++) {
            while (!pq.isEmpty() && pq.peek() < s) {
                pq.poll();
            }
            for (int e : g.getOrDefault(s, List.of())) {
                pq.offer(e);
            }
            if (!pq.isEmpty()) {
                pq.poll();
                ans++;
            }
        }
        return ans;
    }
}
