class Solution:
    def solveNQueens(self, n: int) -> List[List[str]]:
        res = []
        g = [['.'] * n for _ in range(n)]
        col = [False] * n
        dg = [False] * (2 * n)
        udg = [False] * (2 * n)

        def dfs(u):
            if u == n:
                res.append([''.join(item) for item in g])
                return
            for i in range(n):
                if not col[i] and not dg[u + i] and not udg[n - u + i]:
                    g[u][i] = 'Q'
                    col[i] = dg[u + i] = udg[n - u + i] = True
                    dfs(u + 1)
                    g[u][i] = '.'
                    col[i] = dg[u + i] = udg[n - u + i] = False

        dfs(0)
        return res
