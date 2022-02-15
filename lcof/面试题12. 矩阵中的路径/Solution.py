class Solution:
    def exist(self, board: List[List[str]], word: str) -> bool:
        def dfs(i, j, k):
            if k == len(word):
                return True
            if i < 0 or i >= m or j < 0 or j >= n or word[k] != board[i][j]:
                return False
            board[i][j] = ''
            ans = any(
                dfs(i + a, j + b, k + 1) for a, b in [[0, -1], [0, 1], [1, 0], [-1, 0]]
            )
            board[i][j] = word[k]
            return ans

        m, n = len(board), len(board[0])
        return any(dfs(i, j, 0) for i in range(m) for j in range(n))
