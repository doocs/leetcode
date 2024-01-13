class Solution:
    def spiralOrder(self, matrix: List[List[int]]) -> List[int]:
        if not matrix or not matrix[0]:
            return []
        dirs = (0, 1, 0, -1, 0)
        m, n = len(matrix), len(matrix[0])
        vis = [[False] * n for _ in range(m)]
        ans = []
        i = j = k = 0
        for _ in range(m * n):
            ans.append(matrix[i][j])
            vis[i][j] = True
            x, y = i + dirs[k], j + dirs[k + 1]
            if x < 0 or y < 0 or x >= m or y >= n or vis[x][y]:
                k = (k + 1) % 4
                x, y = i + dirs[k], j + dirs[k + 1]
            i, j = x, y
        return ans
