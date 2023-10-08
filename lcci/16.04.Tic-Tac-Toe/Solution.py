class Solution:
    def tictactoe(self, board: List[str]) -> str:
        n = len(board)
        rows = [0] * n
        cols = [0] * n
        dg = udg = 0
        has_empty_grid = False
        for i, row in enumerate(board):
            for j, c in enumerate(row):
                v = 1 if c == 'X' else -1
                if c == ' ':
                    has_empty_grid = True
                    v = 0
                rows[i] += v
                cols[j] += v
                if i == j:
                    dg += v
                if i + j + 1 == n:
                    udg += v
                if (
                    abs(rows[i]) == n
                    or abs(cols[j]) == n
                    or abs(dg) == n
                    or abs(udg) == n
                ):
                    return c
        return 'Pending' if has_empty_grid else 'Draw'
