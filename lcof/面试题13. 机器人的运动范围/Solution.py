class Solution:
    cnt = 0
    def movingCount(self, m: int, n: int, k: int) -> int:
        def cal(m, n):
            s = str(m) + str(n)
            return sum([int(i) for i in s])
        def dfs(i, j):
            if i < 0 or i >= m or j < 0 or j >= n or cal(i, j) > k or visited[i][j]:
                return
            self.cnt += 1
            visited[i][j] = True
            dfs(i + 1, j)
            dfs(i - 1, j)
            dfs(i, j + 1)
            dfs(i, j - 1)
        self.cnt = 0
        visited = [[False for _ in range(n)] for _ in range(m)]
        dfs(0, 0)
        return self.cnt
