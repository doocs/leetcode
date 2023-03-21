class Solution:
    def countOrders(self, n: int) -> int:
        mod = 10**9 + 7
        f = 1
        for i in range(2, n + 1):
            f = (f * i * (2 * i - 1)) % mod
        return f
