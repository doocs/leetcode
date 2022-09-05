/**
 * // This is the GridMaster's API interface.
 * // You should not implement it, or speculate about its implementation
 * class GridMaster {
 *     boolean canMove(char direction);
 *     void move(char direction);
 *     boolean isTarget();
 * }
 */

class Solution {
    private static final char[] dir = {'U', 'R', 'D', 'L'};
    private static final char[] ndir = {'D', 'L', 'U', 'R'};
    private static final int[] dirs = {-1, 0, 1, 0, -1};
    private static final int N = 1010;
    private Set<Integer> s;
    private int[] target;

    public int findShortestPath(GridMaster master) {
        target = null;
        s = new HashSet<>();
        s.add(0);
        dfs(0, 0, master);
        if (target == null) {
            return -1;
        }
        s.remove(0);
        Deque<int[]> q = new ArrayDeque<>();
        q.offer(new int[] {0, 0});
        int ans = -1;
        while (!q.isEmpty()) {
            ++ans;
            for (int n = q.size(); n > 0; --n) {
                int[] p = q.poll();
                int i = p[0], j = p[1];
                if (target[0] == i && target[1] == j) {
                    return ans;
                }
                for (int k = 0; k < 4; ++k) {
                    int x = i + dirs[k], y = j + dirs[k + 1];
                    if (s.contains(x * N + y)) {
                        s.remove(x * N + y);
                        q.offer(new int[] {x, y});
                    }
                }
            }
        }
        return -1;
    }

    private void dfs(int i, int j, GridMaster master) {
        if (master.isTarget()) {
            target = new int[] {i, j};
        }
        for (int k = 0; k < 4; ++k) {
            char d = dir[k], nd = ndir[k];
            int x = i + dirs[k], y = j + dirs[k + 1];
            if (master.canMove(d) && !s.contains(x * N + y)) {
                s.add(x * N + y);
                master.move(d);
                dfs(x, y, master);
                master.move(nd);
            }
        }
    }
}