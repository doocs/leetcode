class Solution:
    def spiralOrder(self, matrix: List[List[int]]) -> List[int]:
        m, n = len(matrix), len(matrix[0])
        dirs = (0, 1, 0, -1, 0)
        i = j = k = 0
        ans = []
        for _ in range(m * n):
            ans.append(matrix[i][j])
            matrix[i][j] += 300
            x, y = i + dirs[k], j + dirs[k + 1]
            if x < 0 or x >= m or y < 0 or y >= n or matrix[x][y] > 100:
                k = (k + 1) % 4
            i += dirs[k]
            j += dirs[k + 1]
        for i in range(m):
            for j in range(n):
                matrix[i][j] -= 300
        return ans
