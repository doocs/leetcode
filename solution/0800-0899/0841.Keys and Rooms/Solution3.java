class Solution {
    public boolean canVisitAllRooms(List<List<Integer>> rooms) {
        int n = rooms.size();
        boolean[] vis = new boolean[n];
        Deque<Integer> stk = new ArrayDeque<>();
        stk.push(0);
        while (!stk.isEmpty()) {
            int i = stk.pop();
            if (vis[i]) {
                continue;
            }
            vis[i] = true;
            for (int j : rooms.get(i)) {
                stk.push(j);
            }
        }
        for (boolean v : vis) {
            if (!v) {
                return false;
            }
        }
        return true;
    }
}
