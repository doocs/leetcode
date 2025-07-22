class Solution:
    def maxValue(self, nums: List[int], k: int) -> int:
        m = 1 << 7
        n = len(nums)
        f = [[[False] * m for _ in range(k + 2)] for _ in range(n + 1)]
        f[0][0][0] = True
        for i in range(n):
            for j in range(k + 1):
                for x in range(m):
                    f[i + 1][j][x] |= f[i][j][x]
                    f[i + 1][j + 1][x | nums[i]] |= f[i][j][x]

        g = [[[False] * m for _ in range(k + 2)] for _ in range(n + 1)]
        g[n][0][0] = True
        for i in range(n, 0, -1):
            for j in range(k + 1):
                for y in range(m):
                    g[i - 1][j][y] |= g[i][j][y]
                    g[i - 1][j + 1][y | nums[i - 1]] |= g[i][j][y]

        ans = 0
        for i in range(k, n - k + 1):
            for x in range(m):
                if f[i][k][x]:
                    for y in range(m):
                        if g[i][k][y]:
                            ans = max(ans, x ^ y)
        return ans
