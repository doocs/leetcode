class Solution {
    public boolean canVisitAllRooms(List<List<Integer>> rooms) {
        int n = rooms.size();
        boolean[] vis = new boolean[n];
        Deque<Integer> q = new ArrayDeque<>();
        q.offer(0);
        int cnt = 0;
        while (!q.isEmpty()) {
            int i = q.poll();
            if (vis[i]) {
                continue;
            }
            vis[i] = true;
            ++cnt;
            for (int j : rooms.get(i)) {
                q.offer(j);
            }
        }
        return cnt == n;
    }
}
