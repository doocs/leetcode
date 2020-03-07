class Solution:
    def exist(self, board: List[List[str]], word: str) -> bool:
        if not word:
            return False
        rows, cols = len(board), len(board[0])
        visited = [[False for _ in range(cols)] for _ in range(rows)]
        for i in range(rows):
            for j in range(cols):
                if self.visit(board, visited, i, j, rows, cols, word):
                    return True
        return False
    
    def visit(self, board, visited, i, j, rows, cols, word) -> bool:
        if not word:
            return True
        if i < 0 or j < 0 or i >= rows or j >= cols or visited[i][j] or board[i][j] != word[0]:
            return False
        visited[i][j] = True
        res = self.visit(board, visited, i - 1, j, rows, cols, word[1:]) or self.visit(board, visited, i + 1, j, rows, cols, word[1:]) or self.visit(board, visited, i, j - 1, rows, cols, word[1:]) or self.visit(board, visited, i, j + 1, rows, cols, word[1:])
        visited[i][j] = res
        return res