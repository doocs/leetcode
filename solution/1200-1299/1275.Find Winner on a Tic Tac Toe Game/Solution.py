class Solution:
    def tictactoe(self, moves: List[List[int]]) -> str:
        n = len(moves)
        counter = [0] * 8
        for i in range(n - 1, -1, -2):
            row, col = moves[i][0], moves[i][1]
            counter[row] += 1
            counter[col + 3] += 1
            if row == col:
                counter[6] += 1
            if row + col == 2:
                counter[7] += 1
            if (
                counter[row] == 3
                or counter[col + 3] == 3
                or counter[6] == 3
                or counter[7] == 3
            ):
                return "A" if (i % 2) == 0 else "B"
        return "Draw" if n == 9 else "Pending"
