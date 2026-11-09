class Solution:
    def luckyNumbers(self, matrix: list[list[int]]) -> list[int]:
        rows = {min(row) for row in matrix}
        cols = {max(col) for col in zip(*matrix)}
        return list(rows & cols)
