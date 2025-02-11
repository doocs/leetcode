class Solution {
    private final int[] dirs = {-1, 0, 1, 0, -1};

    public boolean canMouseWin(String[] grid, int catJump, int mouseJump) {
        int m = grid.length;
        int n = grid[0].length();
        int catStart = 0, mouseStart = 0, food = 0;
        List<Integer>[] gMouse = new List[m * n];
        List<Integer>[] gCat = new List[m * n];
        Arrays.setAll(gMouse, i -> new ArrayList<>());
        Arrays.setAll(gCat, i -> new ArrayList<>());

        for (int i = 0; i < m; i++) {
            for (int j = 0; j < n; j++) {
                char c = grid[i].charAt(j);
                if (c == '#') {
                    continue;
                }
                int v = i * n + j;
                if (c == 'C') {
                    catStart = v;
                } else if (c == 'M') {
                    mouseStart = v;
                } else if (c == 'F') {
                    food = v;
                }

                for (int d = 0; d < 4; ++d) {
                    for (int k = 0; k <= mouseJump; k++) {
                        int x = i + k * dirs[d];
                        int y = j + k * dirs[d + 1];
                        if (x < 0 || x >= m || y < 0 || y >= n || grid[x].charAt(y) == '#') {
                            break;
                        }
                        gMouse[v].add(x * n + y);
                    }
                    for (int k = 0; k <= catJump; k++) {
                        int x = i + k * dirs[d];
                        int y = j + k * dirs[d + 1];
                        if (x < 0 || x >= m || y < 0 || y >= n || grid[x].charAt(y) == '#') {
                            break;
                        }
                        gCat[v].add(x * n + y);
                    }
                }
            }
        }

        return calc(gMouse, gCat, mouseStart, catStart, food) == 1;
    }

    private int calc(
        List<Integer>[] gMouse, List<Integer>[] gCat, int mouseStart, int catStart, int hole) {
        int n = gMouse.length;
        int[][][] degree = new int[n][n][2];
        int[][][] ans = new int[n][n][2];
        Deque<int[]> q = new ArrayDeque<>();

        for (int i = 0; i < n; i++) {
            for (int j = 0; j < n; j++) {
                degree[i][j][0] = gMouse[i].size();
                degree[i][j][1] = gCat[j].size();
            }
        }

        for (int i = 0; i < n; i++) {
            ans[hole][i][1] = 1;
            ans[i][hole][0] = 2;
            ans[i][i][1] = 2;
            ans[i][i][0] = 2;
            q.offer(new int[] {hole, i, 1});
            q.offer(new int[] {i, hole, 0});
            q.offer(new int[] {i, i, 0});
            q.offer(new int[] {i, i, 1});
        }

        while (!q.isEmpty()) {
            int[] state = q.poll();
            int m = state[0], c = state[1], t = state[2];
            int result = ans[m][c][t];
            for (int[] prevState : getPrevStates(gMouse, gCat, state, ans)) {
                int pm = prevState[0], pc = prevState[1], pt = prevState[2];
                if (pt == result - 1) {
                    ans[pm][pc][pt] = result;
                    q.offer(prevState);
                } else {
                    degree[pm][pc][pt]--;
                    if (degree[pm][pc][pt] == 0) {
                        ans[pm][pc][pt] = result;
                        q.offer(prevState);
                    }
                }
            }
        }

        return ans[mouseStart][catStart][0];
    }

    private List<int[]> getPrevStates(
        List<Integer>[] gMouse, List<Integer>[] gCat, int[] state, int[][][] ans) {
        int m = state[0], c = state[1], t = state[2];
        int pt = t ^ 1;
        List<int[]> pre = new ArrayList<>();
        if (pt == 1) {
            for (int pc : gCat[c]) {
                if (ans[m][pc][1] == 0) {
                    pre.add(new int[] {m, pc, pt});
                }
            }
        } else {
            for (int pm : gMouse[m]) {
                if (ans[pm][c][0] == 0) {
                    pre.add(new int[] {pm, c, 0});
                }
            }
        }
        return pre;
    }
}
