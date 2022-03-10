class Solution:
    def cuttingRope(self, n: int) -> int:
        if n < 4:
            return n - 1
        ans = 1
        while n > 4:
            ans *= 3
            n -= 3
        ans *= n
        return ans
