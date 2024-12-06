class Solution:
    def numRookCaptures(self, board: List[List[str]]) -> int:
        dirs = (-1, 0, 1, 0, -1)
        n = len(board)
        for i in range(n):
            for j in range(n):
                if board[i][j] == "R":
                    ans = 0
                    for a, b in pairwise(dirs):
                        x, y = i + a, j + b
                        while 0 <= x < n and 0 <= y < n and board[x][y] != "B":
                            if board[x][y] == "p":
                                ans += 1
                                break
                            x, y = x + a, y + b
                    return ans
