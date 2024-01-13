class Solution:
    def maximumScore(self, a: int, b: int, c: int) -> int:
        s = sorted([a, b, c])
        ans = 0
        while s[1]:
            ans += 1
            s[1] -= 1
            s[2] -= 1
            s.sort()
        return ans
