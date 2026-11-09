class Solution:
    def getNoZeroIntegers(self, n: int) -> list[int]:
        for a in count(1):
            b = n - a
            if "0" not in f"{a}{b}":
                return [a, b]
