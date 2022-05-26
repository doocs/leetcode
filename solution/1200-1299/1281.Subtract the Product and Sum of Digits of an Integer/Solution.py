class Solution:
    def subtractProductAndSum(self, n: int) -> int:
        s, p = 0, 1
        while n:
            t = n % 10
            n //= 10
            s += t
            p *= t
        return p - s
