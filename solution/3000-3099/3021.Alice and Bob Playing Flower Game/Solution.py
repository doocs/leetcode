class Solution:
    def flowerGame(self, n: int, m: int) -> int:
        count = (n + 1) // 2
        tol = (m + 1) // 2
        ecount = n // 2
        etol = m // 2
        return count * etol + ecount * tol
