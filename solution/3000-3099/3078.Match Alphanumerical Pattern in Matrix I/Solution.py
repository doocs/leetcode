class Solution:
    def findPattern(self, board: List[List[int]], pattern: List[str]) -> List[int]:
        def check(i: int, j: int) -> bool:
            d1 = {}
            d2 = {}
            for a in range(r):
                for b in range(c):
                    x, y = i + a, j + b
                    if pattern[a][b].isdigit():
                        if int(pattern[a][b]) != board[x][y]:
                            return False
                    else:
                        if pattern[a][b] in d1 and d1[pattern[a][b]] != board[x][y]:
                            return False
                        if board[x][y] in d2 and d2[board[x][y]] != pattern[a][b]:
                            return False
                        d1[pattern[a][b]] = board[x][y]
                        d2[board[x][y]] = pattern[a][b]
            return True

        m, n = len(board), len(board[0])
        r, c = len(pattern), len(pattern[0])
        for i in range(m - r + 1):
            for j in range(n - c + 1):
                if check(i, j):
                    return [i, j]
        return [-1, -1]
