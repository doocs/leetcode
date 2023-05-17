class Solution:
    def findSquare(self, matrix: List[List[int]]) -> List[int]:
        n = len(matrix)
        down = [[0] * n for _ in range(n)]
        right = [[0] * n for _ in range(n)]
        for i in range(n - 1, -1, -1):
            for j in range(n - 1, -1, -1):
                if matrix[i][j] == 0:
                    down[i][j] = down[i + 1][j] + 1 if i + 1 < n else 1
                    right[i][j] = right[i][j + 1] + 1 if j + 1 < n else 1
        for k in range(n, 0, -1):
            for i in range(n - k + 1):
                for j in range(n - k + 1):
                    if (
                        down[i][j] >= k
                        and right[i][j] >= k
                        and right[i + k - 1][j] >= k
                        and down[i][j + k - 1] >= k
                    ):
                        return [i, j, k]
        return []
