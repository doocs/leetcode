class Solution:
    def countPalindromes(self, s: str) -> int:
        mod = 10**9 + 7
        n = len(s)
        pre = [[[0] * 10 for _ in range(10)] for _ in range(n + 2)]
        suf = [[[0] * 10 for _ in range(10)] for _ in range(n + 2)]
        t = list(map(int, s))
        c = [0] * 10
        for i, v in enumerate(t, 1):
            for j in range(10):
                for k in range(10):
                    pre[i][j][k] = pre[i - 1][j][k]
            for j in range(10):
                pre[i][j][v] += c[j]
            c[v] += 1
        c = [0] * 10
        for i in range(n, 0, -1):
            v = t[i - 1]
            for j in range(10):
                for k in range(10):
                    suf[i][j][k] = suf[i + 1][j][k]
            for j in range(10):
                suf[i][j][v] += c[j]
            c[v] += 1
        ans = 0
        for i in range(1, n + 1):
            for j in range(10):
                for k in range(10):
                    ans += pre[i - 1][j][k] * suf[i + 1][j][k]
                    ans %= mod
        return ans
