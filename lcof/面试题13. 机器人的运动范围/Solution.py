class Solution:

    def __init__(self):
        self._cnt = 0

    def movingCount(self, m: int, n: int, k: int) -> int:
        self._cnt = 0
        visited = [[False for j in range(n)] for i in range(m)]
        self.visit(0, 0, m, n, k, visited)
        return self._cnt
    
    def visit(self, i, j, m, n, k, visited):
        if i < 0 or i >= m or j < 0 or j >= n or visited[i][j] or self.cal(i, j) > k:
            return
        visited[i][j] = True
        self._cnt += 1
        self.visit(i - 1, j, m, n, k, visited)
        self.visit(i + 1, j, m, n, k, visited)
        self.visit(i, j - 1, m, n, k, visited)
        self.visit(i, j + 1, m, n, k, visited)

    def cal(self, m, n) -> int:
        s = str(m) + str(n)
        return sum([int(i) for i in s])