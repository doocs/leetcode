class Solution:
    def minFlips(self, a: int, b: int, c: int) -> int:
        ans = 0
        for i in range(30):
            x, y, z = a >> i & 1, b >> i & 1, c >> i & 1
            if x | y != z:
                ans += 2 if x == 1 and y == 1 else 1
        return ans
