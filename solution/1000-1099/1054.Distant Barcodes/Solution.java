class Solution {
    public int[] rearrangeBarcodes(int[] barcodes) {
        Map<Integer, Integer> cnt = new HashMap<>();
        for (int v : barcodes) {
            cnt.put(v, cnt.getOrDefault(v, 0) + 1);
        }
        PriorityQueue<int[]> pq = new PriorityQueue<>((a, b) -> b[1] - a[1]);
        for (var e : cnt.entrySet()) {
            pq.offer(new int[] {e.getKey(), e.getValue()});
        }
        Deque<int[]> q = new ArrayDeque<>();
        int[] ans = new int[barcodes.length];
        int i = 0;
        while (!pq.isEmpty()) {
            var p = pq.poll();
            ans[i++] = p[0];
            p[1]--;
            q.offer(p);
            while (q.size() > 1) {
                p = q.pollFirst();
                if (p[1] > 0) {
                    pq.offer(p);
                }
            }
        }
        return ans;
    }
}