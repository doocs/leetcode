class Solution {
    public int robotSim(int[] commands, int[][] obstacles) {
        int[][] dirs = {{-1, 0}, {0, 1}, {1, 0}, {0, -1}};
        Set<String> s = new HashSet<>();
        for (int[] v : obstacles) {
            s.add(v[0] + "." + v[1]);
        }
        int ans = 0, p = 1;
        int x = 0, y = 0;
        for (int v : commands) {
            if (v == -2) {
                p = (p + 3) % 4;
            } else if (v == -1) {
                p = (p + 1) % 4;
            } else {
                while (v-- > 0) {
                    int nx = x + dirs[p][0], ny = y + dirs[p][1];
                    if (s.contains(nx + "." + ny)) {
                        break;
                    }
                    x = nx;
                    y = ny;
                    ans = Math.max(ans, x * x + y * y);
                }
            }
        }
        return ans;
    }
}