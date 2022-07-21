class Solution:
    def idealArrays(self, n: int, maxValue: int) -> int:
        @cache
        def dfs(i, cnt):
            res = c[-1][cnt - 1]
            if cnt < n:
                k = 2
                while k * i <= maxValue:
                    res = (res + dfs(k * i, cnt + 1)) % mod
                    k += 1
            return res

        c = [[0] * 16 for _ in range(n)]
        mod = 10**9 + 7
        for i in range(n):
            for j in range(min(16, i + 1)):
                c[i][j] = 1 if j == 0 else (c[i - 1][j] + c[i - 1][j - 1]) % mod
        ans = 0
        for i in range(1, maxValue + 1):
            ans = (ans + dfs(i, 1)) % mod
        return ans
