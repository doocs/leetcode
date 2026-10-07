class Solution:
    def minimizeConcatenatedLength(self, words: List[str]) -> int:
        n = len(words)
        f = [[[0] * 26 for _ in range(26)] for _ in range(n + 1)]
        for i in range(n - 1, 0, -1):
            s = words[i]
            m = len(s)
            c, d = ord(s[0]) - 97, ord(s[-1]) - 97
            for a in range(26):
                for b in range(26):
                    x = f[i + 1][a][d] - (c == b)
                    y = f[i + 1][c][b] - (d == a)
                    f[i][a][b] = m + min(x, y)
        a, b = ord(words[0][0]) - 97, ord(words[0][-1]) - 97
        return len(words[0]) + f[1][a][b]
