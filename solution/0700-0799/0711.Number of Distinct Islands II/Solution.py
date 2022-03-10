class Solution:
    def numDistinctIslands2(self, grid: List[List[int]]) -> int:
        def dfs(i, j, shape):
            shape.append([i, j])
            grid[i][j] = 0
            for a, b in [[1, 0], [-1, 0], [0, 1], [0, -1]]:
                x, y = i + a, j + b
                if 0 <= x < m and 0 <= y < n and grid[x][y] == 1:
                    dfs(x, y, shape)

        def normalize(shape):
            shapes = [[] for _ in range(8)]
            for i, j in shape:
                shapes[0].append([i, j])
                shapes[1].append([i, -j])
                shapes[2].append([-i, j])
                shapes[3].append([-i, -j])
                shapes[4].append([j, i])
                shapes[5].append([j, -i])
                shapes[6].append([-j, i])
                shapes[7].append([-j, -i])
            for e in shapes:
                e.sort()
                for i in range(len(e) - 1, -1, -1):
                    e[i][0] -= e[0][0]
                    e[i][1] -= e[0][1]
            shapes.sort()
            return tuple(tuple(e) for e in shapes[0])

        m, n = len(grid), len(grid[0])
        s = set()
        for i in range(m):
            for j in range(n):
                if grid[i][j]:
                    shape = []
                    dfs(i, j, shape)
                    s.add(normalize(shape))
        return len(s)
