class Solution:
    def winnerSquareGame(self, n: int) -> bool:
        @cache
        def dfs(i):
            if i <= 0:
                return False
            j = 1
            while (k := (i - j * j)) >= 0:
                if not dfs(k):
                    return True
                j += 1
            return False

        return dfs(n)
