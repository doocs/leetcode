class Solution:
    def checkMove(
        self, board: List[List[str]], rMove: int, cMove: int, color: str
    ) -> bool:
        dirs = [(1, 0), (0, 1), (-1, 0), (0, -1), (1, 1), (1, -1), (-1, 1), (-1, -1)]
        n = 8
        for a, b in dirs:
            i, j = rMove, cMove
            t = 0
            while 0 <= i + a < n and 0 <= j + b < n:
                t += 1
                i, j = i + a, j + b
                if board[i][j] in ['.', color]:
                    break
            if board[i][j] == color and t > 1:
                return True
        return False
