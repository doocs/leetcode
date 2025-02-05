class Solution:
    def maxCompatibilitySum(
        self, students: List[List[int]], mentors: List[List[int]]
    ) -> int:
        def dfs(i: int, s: int):
            if i >= m:
                nonlocal ans
                ans = max(ans, s)
                return
            for j in range(m):
                if not vis[j]:
                    vis[j] = True
                    dfs(i + 1, s + g[i][j])
                    vis[j] = False

        ans = 0
        m = len(students)
        vis = [False] * m
        g = [[0] * m for _ in range(m)]
        for i, x in enumerate(students):
            for j, y in enumerate(mentors):
                g[i][j] = sum(a == b for a, b in zip(x, y))
        dfs(0, 0)
        return ans
