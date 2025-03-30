class Solution:
    def reverseDegree(self, s: str) -> int:
        ans = 0
        for i, c in enumerate(s, 1):
            x = 26 - (ord(c) - ord("a"))
            ans += i * x
        return ans
