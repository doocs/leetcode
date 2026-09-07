class Solution:
    def distinctSubseqII(self, s: str) -> int:
        mod = 10**9 + 7
        f = [0] * 26
        ans = 0
        for c in s:
            i = ord(c) - ord("a")
            add = (ans + 1 - f[i]) % mod
            ans = (ans + add) % mod
            f[i] = (f[i] + add) % mod
        return ans
