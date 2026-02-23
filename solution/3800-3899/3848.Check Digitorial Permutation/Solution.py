@cache
def f(x: int) -> int:
    if x < 2:
        return 1
    return x * f(x - 1)


class Solution:
    def isDigitorialPermutation(self, n: int) -> bool:
        x, y = 0, n
        while y:
            x += f(y % 10)
            y //= 10
        return sorted(str(x)) == sorted(str(n))
