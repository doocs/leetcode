class Solution:
    def numSub(self, s: str) -> int:
        ans = cnt = 0
        for c in s:
            if c == "1":
                cnt += 1
            else:
                cnt = 0
            ans += cnt
        return ans % (10**9 + 7)
