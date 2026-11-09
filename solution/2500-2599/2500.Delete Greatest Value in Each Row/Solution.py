class Solution:
    def deleteGreatestValue(self, grid: list[list[int]]) -> int:
        for row in grid:
            row.sort()
        return sum(max(col) for col in zip(*grid))
