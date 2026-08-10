@cache
def dfs(i: int) -> bool:
    if i <= 0:
        return False
    k = isqrt(i)
    return any(not dfs(i - j * j) for j in range(1, k + 1))


class Solution:
    def winnerSquareGame(self, n: int) -> bool:
        return dfs(n)
