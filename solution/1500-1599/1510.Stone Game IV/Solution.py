class Solution:
    def winnerSquareGame(self, n: int) -> bool:
        @cache
        def dfs(i: int) -> bool:
            if i == 0:
                return False
            j = 1
            while j * j <= i:
                if not dfs(i - j * j):
                    return True
                j += 1
            return False

        return dfs(n)
