class Solution {
    public int maxRequests(int[][] requests, int k, int window) {
        Map<Integer, List<Integer>> g = new HashMap<>();
        for (int[] r : requests) {
            int u = r[0], t = r[1];
            g.computeIfAbsent(u, x -> new ArrayList<>()).add(t);
        }

        int ans = requests.length;
        ArrayDeque<Integer> kept = new ArrayDeque<>();

        for (List<Integer> ts : g.values()) {
            Collections.sort(ts);
            kept.clear();
            for (int t : ts) {
                while (!kept.isEmpty() && t - kept.peekFirst() > window) {
                    kept.pollFirst();
                }
                if (kept.size() < k) {
                    kept.addLast(t);
                } else {
                    --ans;
                }
            }
        }
        return ans;
    }
}
