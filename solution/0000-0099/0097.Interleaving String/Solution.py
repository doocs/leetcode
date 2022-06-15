class Solution:
    def isInterleave(self, s1: str, s2: str, s3: str) -> bool:
        m, n = len(s1), len(s2)
        if m + n != len(s3):
            return False

        @cache
        def dfs(i, j):
            if i == m and j == n:
                return True

            return (
                i < m
                and s1[i] == s3[i + j]
                and dfs(i + 1, j)
                or j < n
                and s2[j] == s3[i + j]
                and dfs(i, j + 1)
            )

        return dfs(0, 0)
