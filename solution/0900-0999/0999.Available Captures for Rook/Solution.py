class Solution:
    def numRookCaptures(self, board: List[List[str]]) -> int:
        x, y, n = 0, 0, 8
        for i in range(n):
            for j in range(n):
                if board[i][j] == 'R':
                    x, y = i, j
                    break
        ans = 0
        for a, b in [[0, -1], [0, 1], [-1, 0], [1, 0]]:
            i, j = x, y
            while 0 <= i + a < n and 0 <= j + b < n and board[i + a][j + b] != 'B':
                i, j = i + a, j + b
                if board[i][j] == 'p':
                    ans += 1
                    break
        return ans
