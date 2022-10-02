class Solution:
    def commonFactors(self, a: int, b: int) -> int:
        return sum(a % i == 0 and b % i == 0 for i in range(1, 1001))
