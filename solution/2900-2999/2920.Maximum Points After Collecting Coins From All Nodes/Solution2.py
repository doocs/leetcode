class Solution:
    def maximumPoints(self, edges: List[List[int]], coins: List[int], k: int) -> int:
        n = len(coins)
        g = [[] for _ in range(n)]
        for a, b in edges:
            g[a].append(b)
            g[b].append(a)
        f = [[0] * 15 for _ in range(n)]
        stk = [(0, -1, 0)]
        while stk:
            i, fa, state = stk.pop()
            if state == 0:
                stk.append((i, fa, 1))
                for c in g[i]:
                    if c != fa:
                        stk.append((c, i, 0))
            else:
                for j in range(15):
                    a = (coins[i] >> j) - k
                    b = coins[i] >> (j + 1)
                    for c in g[i]:
                        if c != fa:
                            a += f[c][j]
                            if j < 14:
                                b += f[c][j + 1]
                    f[i][j] = max(a, b)
        return f[0][0]
