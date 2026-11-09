class Solution:
    def heightChecker(self, heights: list[int]) -> int:
        expected = sorted(heights)
        return sum(a != b for a, b in zip(heights, expected))
