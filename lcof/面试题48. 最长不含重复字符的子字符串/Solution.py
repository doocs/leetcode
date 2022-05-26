class Solution:
    def lengthOfLongestSubstring(self, s: str) -> int:
        i = j = res = 0
        chars = set()
        while i < len(s):
            while s[i] in chars:
                if s[j] in chars:
                    chars.remove(s[j])
                j += 1
            chars.add(s[i])
            res = max(res, i - j + 1)
            i += 1
        return res
