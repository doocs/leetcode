class Solution:
    def maxUniqueSplit(self, s: str) -> int:
        def dfs(i, t):
            if i >= len(s):
                nonlocal ans
                ans = max(ans, t)
                return
            for j in range(i + 1, len(s) + 1):
                if s[i:j] not in vis:
                    vis.add(s[i:j])
                    dfs(j, t + 1)
                    vis.remove(s[i:j])

        vis = set()
        ans = 1
        dfs(0, 0)
        return ans
