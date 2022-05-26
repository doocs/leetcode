class Solution:
    def wordPatternMatch(self, pattern: str, s: str) -> bool:
        def dfs(i, j):
            if i == m and j == n:
                return True
            if i == m or j == n or n - j < m - i:
                return False
            for k in range(j, n):
                t = s[j : k + 1]
                if d.get(pattern[i]) == t:
                    if dfs(i + 1, k + 1):
                        return True
                if pattern[i] not in d and t not in vis:
                    d[pattern[i]] = t
                    vis.add(t)
                    if dfs(i + 1, k + 1):
                        return True
                    d.pop(pattern[i])
                    vis.remove(t)
            return False

        m, n = len(pattern), len(s)
        d = {}
        vis = set()
        return dfs(0, 0)
