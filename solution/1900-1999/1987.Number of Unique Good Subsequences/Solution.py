class Solution:
    def numberOfUniqueGoodSubsequences(self, binary: str) -> int:
        f = g = 0
        ans = 0
        mod = 10**9 + 7
        for c in binary:
            if c == "0":
                g = (g + f) % mod
                ans = 1
            else:
                f = (f + g + 1) % mod
        ans = (ans + f + g) % mod
        return ans
