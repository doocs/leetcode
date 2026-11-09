class Solution:
    def transpose(self, matrix: list[list[int]]) -> list[list[int]]:
        return [list(row) for row in zip(*matrix)]
