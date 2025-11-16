class Solution:
    def numSub(self, s: str) -> int:
        mod = 10**9 + 7
        ans = cur = 0
        for c in s:
            if c == "0":
                cur = 0
            else:
                cur += 1
                ans = (ans + cur) % mod
        return ans
