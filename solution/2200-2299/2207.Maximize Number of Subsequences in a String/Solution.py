class Solution:
    def maximumSubsequenceCount(self, text: str, pattern: str) -> int:
        ans = x = y = 0
        for c in text:
            if c == pattern[1]:
                y += 1
                ans += x
            if c == pattern[0]:
                x += 1
        ans += max(x, y)
        return ans
