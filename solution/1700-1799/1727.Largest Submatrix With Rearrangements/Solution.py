class Solution:
    def largestSubmatrix(self, matrix: List[List[int]]) -> int:
        for i in range(1, len(matrix)):
            for j in range(len(matrix[0])):
                if matrix[i][j]:
                    matrix[i][j] = matrix[i - 1][j] + 1
        ans = 0
        for row in matrix:
            row.sort(reverse=True)
            for j, v in enumerate(row, 1):
                ans = max(ans, j * v)
        return ans
