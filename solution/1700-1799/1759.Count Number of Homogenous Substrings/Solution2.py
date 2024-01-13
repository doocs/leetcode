class Solution:
    def countHomogenous(self, s: str) -> int:
        mod = 10**9 + 7
        ans = cnt = 1
        for a, b in pairwise(s):
            cnt = cnt + 1 if a == b else 1
            ans = (ans + cnt) % mod
        return ans
