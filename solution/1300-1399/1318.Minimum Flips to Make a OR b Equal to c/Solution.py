class Solution:
    def minFlips(self, a: int, b: int, c: int) -> int:
        ans = 0
        for i in range(31):
            x, y, z = (a >> i) & 1, (b >> i) & 1, (c >> i) & 1
            if (x | y) == z:
                continue
            if x == 1 and y == 1 and z == 0:
                ans += 2
            else:
                ans += 1
        return ans
