class Solution:
    def numberOfArrays(self, s: str, k: int) -> int:
        mod = 10**9 + 7
        n = len(s)
        f = [0] * (n + 1)
        f[n] = 1
        for i in range(n - 1, -1, -1):
            if s[i] == '0':
                continue
            x = 0
            for j in range(i, n):
                x = x * 10 + int(s[j])
                if x > k:
                    break
                f[i] = (f[i] + f[j + 1]) % mod
        return f[0]
