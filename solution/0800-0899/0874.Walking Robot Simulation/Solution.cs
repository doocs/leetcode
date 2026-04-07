public class Solution {
    public int RobotSim(int[] commands, int[][] obstacles) {
        int[] dirs = {0, 1, 0, -1, 0};
        HashSet<int> s = new HashSet<int>();

        int F(int x, int y) => x * 60010 + y;

        foreach (var o in obstacles) {
            s.Add(F(o[0], o[1]));
        }

        int x = 0, y = 0, k = 0;
        int ans = 0;

        foreach (int c0 in commands) {
            int c = c0;
            if (c == -2) {
                k = (k + 3) % 4;
            } else if (c == -1) {
                k = (k + 1) % 4;
            } else {
                while (c-- > 0) {
                    int nx = x + dirs[k];
                    int ny = y + dirs[k + 1];
                    if (s.Contains(F(nx, ny))) {
                        break;
                    }
                    x = nx;
                    y = ny;
                    ans = Math.Max(ans, x * x + y * y);
                }
            }
        }

        return ans;
    }
}
