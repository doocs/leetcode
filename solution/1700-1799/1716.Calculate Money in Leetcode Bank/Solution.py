class Solution:
    def totalMoney(self, n: int) -> int:
        k, b = divmod(n, 7)
        s1 = (28 + 28 + 7 * (k - 1)) * k // 2
        s2 = (k + 1 + k + 1 + b - 1) * b // 2
        return s1 + s2
