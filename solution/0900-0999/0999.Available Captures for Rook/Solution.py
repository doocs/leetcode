class Solution:
    def numRookCaptures(self, board: List[List[str]]) -> int:

        def search(board, i, j, direction):
            while i >= 0 and i < 8 and j >= 0 and j < 8:
                if board[i][j] == 'B':
                    return 0
                if board[i][j] == 'p':
                    return 1
                i += direction[0]
                j += direction[1]
            return 0
        directions = [(-1, 0), (1, 0), (0, -1), (0, 1)]
        res = 0
        for i in range(8):
            for j in range(8):
                if board[i][j] == 'R':
                    for direction in directions:
                        res += search(board, i, j, direction)
                    return res
