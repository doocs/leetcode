class Solution:
    def numOfMinutes(
        self, n: int, headID: int, manager: List[int], informTime: List[int]
    ) -> int:
        def dfs(i):
            ans = 0
            for j in g[i]:
                ans = max(ans, informTime[i] + dfs(j))
            return ans

        g = defaultdict(list)
        for i, m in enumerate(manager):
            g[m].append(i)
        return dfs(headID)
