class Solution {
    private static final int INF = 0x3f3f3f3f;

    public int networkDelayTime(int[][] times, int n, int k) {
        List<List<Pair>> graph = new ArrayList<>();
        for (int i = 0; i < n; i++) {
            graph.add(new ArrayList<>());
        }
        for (int[] t : times) {
            int from = t[0] - 1, to = t[1] - 1, time = t[2];
            graph.get(from).add(new Pair(to, time));
        }

        List<Integer> dis = new ArrayList<>();
        for (int i = 0; i < n; i++) {
            dis.add(INF);
        }
        dis.set(k - 1, 0);

        Queue<Integer> queue = new ArrayDeque<>();
        queue.offer(k - 1);
        while (!queue.isEmpty()) {
            int from = queue.poll();
            for (Pair e : graph.get(from)) {
                int to = e.first, time = e.second;
                if (time + dis.get(from) < dis.get(to)) {
                    dis.set(to, time + dis.get(from));
                    queue.offer(to);
                }
            }
        }

        int ans = Integer.MIN_VALUE;
        for (int d : dis) {
            ans = Math.max(ans, d);
        }

        return ans == INF ? -1 : ans;
    }

    static class Pair {
        private int first;
        private int second;

        public Pair(int first, int second) {
            this.first = first;
            this.second = second;
        }
    }
}
