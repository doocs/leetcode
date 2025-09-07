class Solution:
    def getNoZeroIntegers(self, n: int) -> List[int]:
        def f(x: int) -> bool:
            while x:
                if x % 10 == 0:
                    return False
                x //= 10
            return True

        for a in count(1):
            b = n - a
            if f(a) and f(b):
                return [a, b]
