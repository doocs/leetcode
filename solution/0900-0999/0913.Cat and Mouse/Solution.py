HOLE, MOUSE_START, CAT_START = 0, 1, 2
MOUSE_TURN, CAT_TURN = 0, 1
MOUSE_WIN, CAT_WIN, TIE = 1, 2, 0


class Solution:
    def catMouseGame(self, graph: List[List[int]]) -> int:
        def get_prev_states(state):
            m, c, t = state
            pt = t ^ 1
            pre = []
            if pt == CAT_TURN:
                for pc in graph[c]:
                    if pc != HOLE:
                        pre.append((m, pc, pt))
            else:
                for pm in graph[m]:
                    pre.append((pm, c, pt))
            return pre

        n = len(graph)
        ans = [[[0, 0] for _ in range(n)] for _ in range(n)]
        degree = [[[0, 0] for _ in range(n)] for _ in range(n)]
        for i in range(n):
            for j in range(1, n):
                degree[i][j][MOUSE_TURN] = len(graph[i])
                degree[i][j][CAT_TURN] = len(graph[j])
            for j in graph[HOLE]:
                degree[i][j][CAT_TURN] -= 1
        q = deque()
        for j in range(1, n):
            ans[0][j][MOUSE_TURN] = ans[0][j][CAT_TURN] = MOUSE_WIN
            q.append((0, j, MOUSE_TURN))
            q.append((0, j, CAT_TURN))
        for i in range(1, n):
            ans[i][i][MOUSE_TURN] = ans[i][i][CAT_TURN] = CAT_WIN
            q.append((i, i, MOUSE_TURN))
            q.append((i, i, CAT_TURN))
        while q:
            state = q.popleft()
            t = ans[state[0]][state[1]][state[2]]
            for prev_state in get_prev_states(state):
                pm, pc, pt = prev_state
                if ans[pm][pc][pt] == TIE:
                    win = (t == MOUSE_WIN and pt == MOUSE_TURN) or (
                        t == CAT_WIN and pt == CAT_TURN
                    )
                    if win:
                        ans[pm][pc][pt] = t
                        q.append(prev_state)
                    else:
                        degree[pm][pc][pt] -= 1
                        if degree[pm][pc][pt] == 0:
                            ans[pm][pc][pt] = t
                            q.append(prev_state)
        return ans[MOUSE_START][CAT_START][MOUSE_TURN]
