class Solution:
    def reverseLeftWords(self, s: str, n: int) -> str:
        n %= len(s)
        return s[n:] + s[:n]