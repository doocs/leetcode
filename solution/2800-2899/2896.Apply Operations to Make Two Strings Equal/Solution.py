class Solution:
    def minOperations(self, s1: str, s2: str, x: int) -> int:
        @cache
        def dfs(i: int, j: int) -> int:
            if i > j:
                return 0
            a = dfs(i + 1, j - 1) + x
            b = dfs(i + 2, j) + idx[i + 1] - idx[i]
            c = dfs(i, j - 2) + idx[j] - idx[j - 1]
            return min(a, b, c)

        n = len(s1)
        idx = [i for i in range(n) if s1[i] != s2[i]]
        m = len(idx)
        if m & 1:
            return -1
        return dfs(0, m - 1)
