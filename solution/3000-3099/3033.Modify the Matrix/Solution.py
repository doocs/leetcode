class Solution:
    def modifiedMatrix(self, matrix: List[List[int]]) -> List[List[int]]:
        rows = len(matrix)
        cols = len(matrix[0])
        for i in range(cols):
            max_val = float('-inf')
            for j in range(rows):
                max_val = max(max_val, matrix[j][i])
            for j in range(rows):
                if matrix[j][i] == -1:
                    matrix[j][i] = max_val
        return matrix
