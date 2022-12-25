class Solution:
    def maxCompatibilitySum(
        self, students: List[List[int]], mentors: List[List[int]]
    ) -> int:
        def dfs(i, t):
            if i == m:
                nonlocal ans
                ans = max(ans, t)
                return
            for j in range(m):
                if not vis[j]:
                    vis[j] = True
                    dfs(i + 1, t + g[i][j])
                    vis[j] = False

        m = len(students)
        g = [[0] * m for _ in range(m)]
        for i in range(m):
            for j in range(m):
                g[i][j] = sum(a == b for a, b in zip(students[i], mentors[j]))
        vis = [False] * m
        ans = 0
        dfs(0, 0)
        return ans
