class Solution:
    def findWords(self, board: List[List[str]], words: List[str]) -> List[str]:
        def check(word):
            cnt = Counter(word)
            return all(counter[c] >= i for c, i in cnt.items())

        def dfs(i, j, l, word):
            if l == len(word):
                return True
            if i < 0 or i >= m or j < 0 or j >= n or board[i][j] != word[l]:
                return False
            c = board[i][j]
            board[i][j] = '0'
            ans = False
            for a, b in [[0, -1], [0, 1], [1, 0], [-1, 0]]:
                x, y = i + a, j + b
                ans = ans or dfs(x, y, l + 1, word)
            board[i][j] = c
            return ans

        def find(word):
            if not check(word):
                return False
            for i in range(m):
                for j in range(n):
                    if dfs(i, j, 0, word):
                        return True
            return False

        m, n = len(board), len(board[0])
        words = set(words)
        counter = Counter(c for b in board for c in b)
        return [word for word in words if find(word)]
