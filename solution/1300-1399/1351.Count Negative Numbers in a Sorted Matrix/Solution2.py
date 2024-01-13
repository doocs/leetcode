class Solution:
    def countNegatives(self, grid: List[List[int]]) -> int:
        return sum(bisect_left(row[::-1], 0) for row in grid)
