class Solution:
    def repairCars(self, ranks: List[int], cars: int) -> int:
        def check(t):
            return sum(int(sqrt(t // r)) for r in ranks) >= cars

        return bisect_left(range(ranks[0] * cars * cars), True, key=check)
