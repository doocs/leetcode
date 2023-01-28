class Solution:
    def countAsterisks(self, s: str) -> int:
        ans, ok = 0, 1
        for c in s:
            if c == "*":
                ans += ok
            elif c == "|":
                ok ^= 1
        return ans
