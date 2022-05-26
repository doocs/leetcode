class Solution:
    def checkValid(self, matrix: List[List[int]]) -> bool:
        n = len(matrix)
        for i in range(n):
            seen = [False] * n
            for j in range(n):
                v = matrix[i][j] - 1
                if seen[v]:
                    return False
                seen[v] = True
        for j in range(n):
            seen = [False] * n
            for i in range(n):
                v = matrix[i][j] - 1
                if seen[v]:
                    return False
                seen[v] = True
        return True
