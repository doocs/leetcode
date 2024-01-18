class Solution:
    def findChampion(self, grid: List[List[int]]) -> int:
        for i, row in enumerate(grid):
            if all(x == 1 for j, x in enumerate(row) if i != j):
                return i
