class Solution:
    def canMouseWin(self, grid: List[str], catJump: int, mouseJump: int) -> bool:
        m, n = len(grid), len(grid[0])
        cat_start = mouse_start = food = 0
        dirs = (-1, 0, 1, 0, -1)
        g_mouse = [[] for _ in range(m * n)]
        g_cat = [[] for _ in range(m * n)]

        for i, row in enumerate(grid):
            for j, c in enumerate(row):
                if c == "#":
                    continue
                v = i * n + j
                if c == "C":
                    cat_start = v
                elif c == "M":
                    mouse_start = v
                elif c == "F":
                    food = v
                for a, b in pairwise(dirs):
                    for k in range(mouseJump + 1):
                        x, y = i + k * a, j + k * b
                        if not (0 <= x < m and 0 <= y < n and grid[x][y] != "#"):
                            break
                        g_mouse[v].append(x * n + y)
                    for k in range(catJump + 1):
                        x, y = i + k * a, j + k * b
                        if not (0 <= x < m and 0 <= y < n and grid[x][y] != "#"):
                            break
                        g_cat[v].append(x * n + y)
        return self.calc(g_mouse, g_cat, mouse_start, cat_start, food) == 1

    def calc(
        self,
        g_mouse: List[List[int]],
        g_cat: List[List[int]],
        mouse_start: int,
        cat_start: int,
        hole: int,
    ) -> int:
        def get_prev_states(state):
            m, c, t = state
            pt = t ^ 1
            pre = []
            if pt == 1:
                for pc in g_cat[c]:
                    if ans[m][pc][1] == 0:
                        pre.append((m, pc, pt))
            else:
                for pm in g_mouse[m]:
                    if ans[pm][c][0] == 0:
                        pre.append((pm, c, 0))
            return pre

        n = len(g_mouse)
        degree = [[[0, 0] for _ in range(n)] for _ in range(n)]
        for i in range(n):
            for j in range(n):
                degree[i][j][0] = len(g_mouse[i])
                degree[i][j][1] = len(g_cat[j])

        ans = [[[0, 0] for _ in range(n)] for _ in range(n)]
        q = deque()
        for i in range(n):
            ans[hole][i][1] = 1
            ans[i][hole][0] = 2
            ans[i][i][1] = ans[i][i][0] = 2
            q.append((hole, i, 1))
            q.append((i, hole, 0))
            q.append((i, i, 0))
            q.append((i, i, 1))
        while q:
            state = q.popleft()
            t = ans[state[0]][state[1]][state[2]]
            for prev_state in get_prev_states(state):
                pm, pc, pt = prev_state
                if pt == t - 1:
                    ans[pm][pc][pt] = t
                    q.append(prev_state)
                else:
                    degree[pm][pc][pt] -= 1
                    if degree[pm][pc][pt] == 0:
                        ans[pm][pc][pt] = t
                        q.append(prev_state)
        return ans[mouse_start][cat_start][0]
