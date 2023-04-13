class Solution:
    def numRookCaptures(self, board: List[List[str]]) -> int:
        ans = 0
        dirs = (-1, 0, 1, 0, -1)
        for i in range(8):
            for j in range(8):
                if board[i][j] == "R":
                    for a, b in pairwise(dirs):
                        x, y = i, j
                        while 0 <= x + a < 8 and 0 <= y + b < 8:
                            x, y = x + a, y + b
                            if board[x][y] == "p":
                                ans += 1
                                break
                            if board[x][y] == "B":
                                break
        return ans
