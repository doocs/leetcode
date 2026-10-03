class Solution:
    def numWays(self, words: List[str], target: str) -> int:
        m, n = len(target), len(words[0])
        cnt = [[0] * 26 for _ in range(n)]
        for w in words:
            for j, c in enumerate(w):
                cnt[j][ord(c) - ord('a')] += 1
        mod = 10**9 + 7
        f = [[0] * (n + 1) for _ in range(m + 1)]
        for j in range(n + 1):
            f[m][j] = 1
        for i in range(m - 1, -1, -1):
            for j in range(n - 1, -1, -1):
                ans = f[i + 1][j + 1] * cnt[j][ord(target[i]) - ord('a')]
                f[i][j] = (ans + f[i][j + 1]) % mod
        return f[0][0]
