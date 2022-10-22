class Solution:
    def maxTaxiEarnings(self, n: int, rides: List[List[int]]) -> int:
        @cache
        def dfs(i):
            if i >= len(rides):
                return 0
            s, e, t = rides[i]
            j = bisect_left(rides, e, lo=i + 1, key=lambda x: x[0])
            return max(dfs(i + 1), dfs(j) + e - s + t)

        rides.sort()
        return dfs(0)
