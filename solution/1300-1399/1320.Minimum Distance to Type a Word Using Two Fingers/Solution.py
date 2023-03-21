class Solution:
    def minimumDistance(self, word: str) -> int:
        def dist(a: int, b: int) -> int:
            x1, y1 = divmod(a, 6)
            x2, y2 = divmod(b, 6)
            return abs(x1 - x2) + abs(y1 - y2)

        n = len(word)
        f = [[[inf] * 26 for _ in range(26)] for _ in range(n)]
        for j in range(26):
            f[0][ord(word[0]) - ord('A')][j] = 0
            f[0][j][ord(word[0]) - ord('A')] = 0
        for i in range(1, n):
            a, b = ord(word[i - 1]) - ord('A'), ord(word[i]) - ord('A')
            d = dist(a, b)
            for j in range(26):
                f[i][b][j] = min(f[i][b][j], f[i - 1][a][j] + d)
                f[i][j][b] = min(f[i][j][b], f[i - 1][j][a] + d)
                if j == a:
                    for k in range(26):
                        t = dist(k, b)
                        f[i][b][j] = min(f[i][b][j], f[i - 1][k][a] + t)
                        f[i][j][b] = min(f[i][j][b], f[i - 1][a][k] + t)
        a = min(f[n - 1][ord(word[-1]) - ord('A')])
        b = min(f[n - 1][j][ord(word[-1]) - ord('A')] for j in range(26))
        return int(min(a, b))
