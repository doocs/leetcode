class Solution:
    def rotatedDigits(self, n: int) -> int:
        def check(x):
            y, t = 0, x
            k = 1
            while t:
                v = t % 10
                if v not in d:
                    return False
                y = d[v] * k + y
                k *= 10
                t //= 10
            return x != y

        d = {0: 0, 1: 1, 8: 8, 2: 5, 5: 2, 6: 9, 9: 6}
        return sum(check(i) for i in range(1, n + 1))
