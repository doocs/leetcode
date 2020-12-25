class Solution:
    def exist(self, board: List[List[str]], word: str) -> bool:
        def dfs(i, j, cur):
            if cur == len(word):
                return True
            if i < 0 or i >= m or j < 0 or j >= n or visited[i][j] or word[cur] != board[i][j]:
                return False
            visited[i][j] = True
            next = cur + 1
            res = dfs(i + 1, j, next) or dfs(i - 1, j, next) or dfs(i, j + 1, next) or dfs(i, j - 1, next)
            visited[i][j] = False
            return res
        m, n = len(board), len(board[0])
        visited = [[False for _ in range(n)] for _ in range(m)]
        for i in range(m):
            for j in range(n):
                res = dfs(i, j, 0)
                if res:
                    return True
        return False
