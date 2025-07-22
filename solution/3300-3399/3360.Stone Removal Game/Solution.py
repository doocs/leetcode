class Solution:
    def canAliceWin(self, n: int) -> bool:
        x, k = 10, 0
        while n >= x:
            n -= x
            x -= 1
            k += 1
        return k % 2 == 1
