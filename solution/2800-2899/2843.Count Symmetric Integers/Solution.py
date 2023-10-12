class Solution:
    def countSymmetricIntegers(self, low: int, high: int) -> int:
        def f(x: int) -> bool:
            s = str(x)
            if len(s) & 1:
                return False
            n = len(s) // 2
            return sum(map(int, s[:n])) == sum(map(int, s[n:]))

        return sum(f(x) for x in range(low, high + 1))
