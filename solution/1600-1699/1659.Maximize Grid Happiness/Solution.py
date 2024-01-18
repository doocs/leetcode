class Solution:
    def getMaxGridHappiness(
        self, m: int, n: int, introvertsCount: int, extrovertsCount: int
    ) -> int:
        @cache
        def dfs(i: int, pre: int, ic: int, ec: int) -> int:
            if i == m or (ic == 0 and ec == 0):
                return 0
            ans = 0
            for cur in range(mx):
                if ix[cur] <= ic and ex[cur] <= ec:
                    a = f[cur] + g[pre][cur]
                    b = dfs(i + 1, cur, ic - ix[cur], ec - ex[cur])
                    ans = max(ans, a + b)
            return ans

        mx = pow(3, n)
        f = [0] * mx
        g = [[0] * mx for _ in range(mx)]
        h = [[0, 0, 0], [0, -60, -10], [0, -10, 40]]
        bits = [[0] * n for _ in range(mx)]
        ix = [0] * mx
        ex = [0] * mx
        for i in range(mx):
            mask = i
            for j in range(n):
                mask, x = divmod(mask, 3)
                bits[i][j] = x
                if x == 1:
                    ix[i] += 1
                    f[i] += 120
                elif x == 2:
                    ex[i] += 1
                    f[i] += 40
                if j:
                    f[i] += h[x][bits[i][j - 1]]
        for i in range(mx):
            for j in range(mx):
                for k in range(n):
                    g[i][j] += h[bits[i][k]][bits[j][k]]
        return dfs(0, 0, introvertsCount, extrovertsCount)
