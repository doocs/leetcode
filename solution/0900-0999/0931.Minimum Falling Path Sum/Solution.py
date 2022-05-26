class Solution:
    def minFallingPathSum(self, matrix: List[List[int]]) -> int:
        n = len(matrix)
        for i in range(1, n):
            for j in range(n):
                mi = matrix[i - 1][j]
                if j > 0:
                    mi = min(mi, matrix[i - 1][j - 1])
                if j < n - 1:
                    mi = min(mi, matrix[i - 1][j + 1])
                matrix[i][j] += mi
        return min(matrix[n - 1])
