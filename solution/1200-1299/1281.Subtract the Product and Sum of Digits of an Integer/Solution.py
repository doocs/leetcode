class Solution:
    def subtractProductAndSum(self, n: int) -> int:
        x, y = 1, 0
        while n:
            n, v = divmod(n, 10)
            x *= v
            y += v
        return x - y
