class Solution:
    def equalPairs(self, grid: List[List[int]]) -> int:
        g = [list(col) for col in zip(*grid)]
        return sum(row == col for row in grid for col in g)
