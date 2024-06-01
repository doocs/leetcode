class TicTacToe:

    def __init__(self, n: int):
        self.n = n
        self.cnt = [defaultdict(int), defaultdict(int)]

    def move(self, row: int, col: int, player: int) -> int:
        cur = self.cnt[player - 1]
        n = self.n
        cur[row] += 1
        cur[n + col] += 1
        if row == col:
            cur[n << 1] += 1
        if row + col == n - 1:
            cur[n << 1 | 1] += 1
        if any(cur[i] == n for i in (row, n + col, n << 1, n << 1 | 1)):
            return player
        return 0


# Your TicTacToe object will be instantiated and called as such:
# obj = TicTacToe(n)
# param_1 = obj.move(row,col,player)
