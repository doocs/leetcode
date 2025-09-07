class Solution:
    def getNoZeroIntegers(self, n: int) -> List[int]:
        for a in count(1):
            b = n - a
            if "0" not in f"{a}{b}":
                return [a, b]
