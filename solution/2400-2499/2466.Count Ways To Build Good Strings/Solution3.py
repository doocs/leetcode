class Solution:
    def countGoodStrings(self, low: int, high: int, zero: int, one: int) -> int:
        mod = 10**9 + 7
        f = [0] * (high + 1)
        for i in range(high, -1, -1):
            ans = int(low <= i <= high)
            if i + zero <= high:
                ans += f[i + zero]
            if i + one <= high:
                ans += f[i + one]
            f[i] = ans % mod
        return f[0]
