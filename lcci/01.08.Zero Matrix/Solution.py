class Solution:
    def setZeroes(self, matrix: List[List[int]]) -> None:
        m, n = len(matrix), len(matrix[0])
        rows = [0] * m
        cols = [0] * n
        for i, row in enumerate(matrix):
            for j, v in enumerate(row):
                if v == 0:
                    rows[i] = cols[j] = 1
        for i in range(m):
            for j in range(n):
                if rows[i] or cols[j]:
                    matrix[i][j] = 0
