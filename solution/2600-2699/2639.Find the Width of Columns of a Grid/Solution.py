class Solution:
    def findColumnWidth(self, grid: list[list[int]]) -> list[int]:
        return [max(len(str(x)) for x in col) for col in zip(*grid)]
