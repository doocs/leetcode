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
    private int[] target;
    private GridMaster master;
    private final int n = 2010;
    private final String s = "URDL";
    private final int[] dirs = {-1, 0, 1, 0, -1};
    private final Set<Integer> vis = new HashSet<>();

    public int findShortestPath(GridMaster master) {
        this.master = master;
        dfs(0, 0);
        if (target == null) {
            return -1;
        }
        vis.remove(0);
        Deque<int[]> q = new ArrayDeque<>();
        q.offer(new int[] {0, 0});
        for (int ans = 0; !q.isEmpty(); ++ans) {
            for (int m = q.size(); m > 0; --m) {
                var p = q.poll();
                if (p[0] == target[0] && p[1] == target[1]) {
                    return ans;
                }
                for (int k = 0; k < 4; ++k) {
                    int x = p[0] + dirs[k], y = p[1] + dirs[k + 1];
                    if (vis.remove(x * n + y)) {
                        q.offer(new int[] {x, y});
                    }
                }
            }
        }
        return -1;
    }

    private void dfs(int i, int j) {
        if (master.isTarget()) {
            target = new int[] {i, j};
            return;
        }
        for (int k = 0; k < 4; ++k) {
            int x = i + dirs[k], y = j + dirs[k + 1];
            if (master.canMove(s.charAt(k)) && vis.add(x * n + y)) {
                master.move(s.charAt(k));
                dfs(x, y);
                master.move(s.charAt((k + 2) % 4));
            }
        }
    }
}