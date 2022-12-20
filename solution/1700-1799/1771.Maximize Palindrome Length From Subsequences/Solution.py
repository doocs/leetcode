class Solution:
    def longestPalindrome(self, word1: str, word2: str) -> int:
        s = word1 + word2
        n = len(s)
        f = [[0] * n for _ in range(n)]
        for i in range(n):
            f[i][i] = 1
        ans = 0
        for i in range(n - 1, -1, -1):
            for j in range(i + 1, n):
                if s[i] == s[j]:
                    f[i][j] = f[i + 1][j - 1] + 2
                    if i < len(word1) and j >= len(word1):
                        ans = max(ans, f[i][j])
                else:
                    f[i][j] = max(f[i + 1][j], f[i][j - 1])
        return ans
