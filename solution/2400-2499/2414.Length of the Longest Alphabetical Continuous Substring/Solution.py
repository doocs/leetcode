class Solution:
    def longestContinuousSubstring(self, s: str) -> int:
        ans = 0
        i, j = 0, 1
        while j < len(s):
            ans = max(ans, j - i)
            if ord(s[j]) - ord(s[j - 1]) != 1:
                i = j
            j += 1
        ans = max(ans, j - i)
        return ans
