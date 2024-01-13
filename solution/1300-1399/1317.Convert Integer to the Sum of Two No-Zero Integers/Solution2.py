class Solution:
    def getNoZeroIntegers(self, n: int) -> List[int]:
        def f(x):
            while x:
                if x % 10 == 0:
                    return False
                x //= 10
            return True

        for a in range(1, n):
            b = n - a
            if f(a) and f(b):
                return [a, b]
