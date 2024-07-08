class Solution:
    def checkMove(
        self, board: List[List[str]], rMove: int, cMove: int, color: str
    ) -> bool:
        for a in range(-1, 2):
            for b in range(-1, 2):
                if a == 0 and b == 0:
                    continue
                i, j = rMove, cMove
                cnt = 0
                while 0 <= i + a < 8 and 0 <= j + b < 8:
                    cnt += 1
                    i, j = i + a, j + b
                    if cnt > 1 and board[i][j] == color:
                        return True
                    if board[i][j] in (color, "."):
                        break
        return False
