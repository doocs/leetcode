class Solution:
    def mincostTickets(self, days: List[int], costs: List[int]) -> int:
        @cache
        def dfs(i: int) -> int:
            if i >= n:
                return 0
            ans = inf
            for c, v in zip(costs, valid):
                j = bisect_left(days, days[i] + v)
                ans = min(ans, c + dfs(j))
            return ans

        n = len(days)
        valid = [1, 7, 30]
        return dfs(0)
