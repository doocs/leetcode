class Solution:
    def lateFee(self, daysLate: List[int]) -> int:
        def f(x: int) -> int:
            if x == 1:
                return 1
            if x > 5:
                return 3 * x
            return 2 * x

        return sum(f(x) for x in daysLate)
