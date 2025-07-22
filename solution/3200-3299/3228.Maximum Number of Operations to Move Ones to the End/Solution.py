class Solution:
    def maxOperations(self, s: str) -> int:
        ans = cnt = 0
        for i, c in enumerate(s):
            if c == "1":
                cnt += 1
            elif i and s[i - 1] == "1":
                ans += cnt
        return ans
