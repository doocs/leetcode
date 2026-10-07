class Solution:
    def minimumWhiteTiles(self, floor: str, numCarpets: int, carpetLen: int) -> int:
        n = len(floor)
        s = [0] * (n + 1)
        for i, c in enumerate(floor):
            s[i + 1] = s[i] + int(c == "1")
        f = [[0] * (numCarpets + 1) for _ in range(n + 1)]
        for i in range(n - 1, -1, -1):
            for j in range(numCarpets + 1):
                if floor[i] == "0":
                    f[i][j] = f[i + 1][j]
                elif j == 0:
                    f[i][j] = s[n] - s[i]
                else:
                    cover = f[i + carpetLen][j - 1] if i + carpetLen <= n else 0
                    f[i][j] = min(1 + f[i + 1][j], cover)
        return f[0][numCarpets]
