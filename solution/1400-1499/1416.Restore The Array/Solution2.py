class Solution:
    def numberOfArrays(self, s: str, k: int) -> int:
        mod = 10**9 + 7
        n = len(s)
        d = len(str(k))
        f = [0] * (d + 1)
        f[n % (d + 1)] = 1
        for i in range(n - 1, -1, -1):
            ways = 0
            if s[i] != '0':
                value = 0
                for j in range(i, min(n, i + d)):
                    value = value * 10 + int(s[j])
                    if value > k:
                        break
                    ways = (ways + f[(j + 1) % (d + 1)]) % mod
            f[i % (d + 1)] = ways
        return f[0]
