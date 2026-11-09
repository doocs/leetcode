class Solution:
    def successfulPairs(
        self, spells: list[int], potions: list[int], success: int
    ) -> list[int]:
        potions.sort()
        m = len(potions)
        return [m - bisect_left(potions, success / v) for v in spells]
