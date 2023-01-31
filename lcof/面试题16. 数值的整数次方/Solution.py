class Solution:
    def myPow(self, x: float, n: int) -> float:
        def qmi(a, k):
            res = 1
            while k:
                if k & 1:
                    res *= a
                a *= a
                k >>= 1
            return res

        return qmi(x, n) if n >= 0 else 1 / qmi(x, -n)
