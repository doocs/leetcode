class Trie:
    def __init__(self):
        self.children = [None] * 26
        self.w = ''

    def insert(self, w):
        node = self
        for c in w:
            idx = ord(c) - ord('a')
            if node.children[idx] is None:
                node.children[idx] = Trie()
            node = node.children[idx]
        node.w = w


class Solution:
    def findWords(self, board: List[List[str]], words: List[str]) -> List[str]:
        def dfs(node, i, j):
            idx = ord(board[i][j]) - ord('a')
            if node.children[idx] is None:
                return
            node = node.children[idx]
            if node.w:
                ans.add(node.w)
            c = board[i][j]
            board[i][j] = '0'
            for a, b in [[0, -1], [0, 1], [1, 0], [-1, 0]]:
                x, y = i + a, j + b
                if 0 <= x < m and 0 <= y < n and board[x][y] != '0':
                    dfs(node, x, y)
            board[i][y] = c

        trie = Trie()
        for w in words:
            trie.insert(w)
        ans = set()
        m, n = len(board), len(board[0])
        for i in range(m):
            for j in range(n):
                dfs(trie, i, j)
        return list(ans)
