class Solution:
    def placeWordInCrossword(self, board: List[List[str]], word: str) -> bool:
        def check(i, j, a, b):
            x, y = i + a * k, j + b * k
            if 0 <= x < m and 0 <= y < n and board[x][y] != '#':
                return False
            for c in word:
                if (
                    i < 0
                    or i >= m
                    or j < 0
                    or j >= n
                    or (board[i][j] != ' ' and board[i][j] != c)
                ):
                    return False
                i, j = i + a, j + b
            return True

        m, n = len(board), len(board[0])
        k = len(word)
        for i in range(m):
            for j in range(n):
                left_to_right = (j == 0 or board[i][j - 1] == '#') and check(i, j, 0, 1)
                right_to_left = (j == n - 1 or board[i][j + 1] == '#') and check(
                    i, j, 0, -1
                )
                up_to_down = (i == 0 or board[i - 1][j] == '#') and check(i, j, 1, 0)
                down_to_up = (i == m - 1 or board[i + 1][j] == '#') and check(
                    i, j, -1, 0
                )
                if left_to_right or right_to_left or up_to_down or down_to_up:
                    return True
        return False
