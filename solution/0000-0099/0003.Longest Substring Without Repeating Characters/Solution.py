class Solution:
    def lengthOfLongestSubstring(self, s: str) -> int:
        i = ans = 0
        chars = set()
        for j, c in enumerate(s):
            while c in chars:
                chars.remove(s[i])
                i += 1
            chars.add(c)
            ans = max(ans, j - i + 1)
        return ans
