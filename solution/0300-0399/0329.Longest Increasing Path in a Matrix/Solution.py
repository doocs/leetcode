class Solution:
    def longestIncreasingPath(self, matrix: List[List[int]]) -> int:
        m, n = len(matrix), len(matrix[0])
        dirs = (-1, 0, 1, 0, -1)
        outdegree = [[0] * n for _ in range(m)]
        length = [[1] * n for _ in range(m)]
        q = []
        for i in range(m):
            for j in range(n):
                for a, b in pairwise(dirs):
                    x, y = i + a, j + b
                    if 0 <= x < m and 0 <= y < n and matrix[x][y] > matrix[i][j]:
                        outdegree[i][j] += 1
                if outdegree[i][j] == 0:
                    q.append((i, j))

        head = 0
        while head < len(q):
            i, j = q[head]
            head += 1
            for a, b in pairwise(dirs):
                x, y = i + a, j + b
                if 0 <= x < m and 0 <= y < n and matrix[x][y] < matrix[i][j]:
                    length[x][y] = max(length[x][y], length[i][j] + 1)
                    outdegree[x][y] -= 1
                    if outdegree[x][y] == 0:
                        q.append((x, y))
        return max(map(max, length))
