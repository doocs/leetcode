class Solution:
    def minTimeToType(self, word: str) -> int:
        ans, a = len(word), ord("a")
        for c in map(ord, word):
            d = abs(c - a)
            ans += min(d, 26 - d)
            a = c
        return ans
