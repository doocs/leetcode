class Solution:
    def mincostTickets(self, days: List[int], costs: List[int]) -> int:
        @cache
        def dfs(i):
            if i >= len(days):
                return 0
            res = inf
            for c, d in zip(costs, [1, 7, 30]):
                j = bisect_left(days, days[i] + d)
                res = min(res, c + dfs(j))
            return res

        return dfs(0)
