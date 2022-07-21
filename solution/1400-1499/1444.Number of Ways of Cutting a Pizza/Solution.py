class Solution:
    def ways(self, pizza: List[str], k: int) -> int:
        @cache
        def dfs(i, j, k):
            if k == 0:
                return int(s[-1][-1] - s[-1][j] - s[i][-1] + s[i][j] > 0)
            res = 0
            for x in range(i + 1, m):
                if s[x][-1] - s[x][j] - s[i][-1] + s[i][j]:
                    res += dfs(x, j, k - 1)
            for y in range(j + 1, n):
                if s[-1][y] - s[-1][j] - s[i][y] + s[i][j]:
                    res += dfs(i, y, k - 1)
            return res % mod

        mod = 10**9 + 7
        m, n = len(pizza), len(pizza[0])
        s = [[0] * (n + 1) for _ in range(m + 1)]
        for i, row in enumerate(pizza):
            for j, v in enumerate(row):
                s[i + 1][j + 1] = s[i + 1][j] + s[i][j + 1] - s[i][j] + int(v == 'A')
        return dfs(0, 0, k - 1)
