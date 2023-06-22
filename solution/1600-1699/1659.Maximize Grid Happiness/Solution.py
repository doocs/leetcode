class Solution:
    def getMaxGridHappiness(
        self, m: int, n: int, introvertsCount: int, extrovertsCount: int
    ) -> int:
        @cache
        def dfs(pos: int, pre: int, ic: int, ec: int) -> int:
            if pos == m * n or (ic == 0 and ec == 0):
                return 0
            ans = 0
            up = pre // p
            left = 0 if pos % n == 0 else pre % 3
            for i in range(3):
                if (i == 1 and ic == 0) or (i == 2 and ec == 0):
                    continue
                cur = pre % p * 3 + i
                a = h[up][i] + h[left][i]
                b = dfs(pos + 1, cur, ic - (i == 1), ec - (i == 2))
                c = 0
                if i == 1:
                    c = 120
                elif i == 2:
                    c = 40
                ans = max(ans, a + b + c)
            return ans

        p = pow(3, n - 1)
        h = [[0, 0, 0], [0, -60, -10], [0, -10, 40]]
        return dfs(0, 0, introvertsCount, extrovertsCount)
