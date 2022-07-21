class Solution {
    public List<String> topKFrequent(String[] words, int k) {
        Map<String, Integer> cnt = new HashMap<>();
        for (String v : words) {
            cnt.put(v, cnt.getOrDefault(v, 0) + 1);
        }
        PriorityQueue<String> q = new PriorityQueue<>((a, b) -> {
            int d = cnt.get(a) - cnt.get(b);
            return d == 0 ? b.compareTo(a) : d;
        });
        for (String v : cnt.keySet()) {
            q.offer(v);
            if (q.size() > k) {
                q.poll();
            }
        }
        LinkedList<String> ans = new LinkedList<>();
        while (!q.isEmpty()) {
            ans.addFirst(q.poll());
        }
        return ans;
    }
}