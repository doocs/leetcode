class Solution:
    def reverse(self, x: int) -> int:
        y = int(str(abs(x))[::-1])
        res = -y if x < 0 else y
        return 0 if res < -(2**31) or res > 2**31 - 1 else res
