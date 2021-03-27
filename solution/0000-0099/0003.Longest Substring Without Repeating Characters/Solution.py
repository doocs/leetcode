class Solution:
    def lengthOfLongestSubstring(self, s: str) -> int:
        res, chars = 0, dict()
        i = j = 0
        while j < len(s):
            if s[j] in chars:
                i = max(i, chars[s[j]] + 1)
            res = max(res, j - i + 1)
            chars[s[j]] = j
            j += 1
        return res
