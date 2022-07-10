class Solution:
    def countRoutes(
        self, locations: List[int], start: int, finish: int, fuel: int
    ) -> int:
        @cache
        def dfs(i, t):
            if abs(locations[i] - locations[finish]) > t:
                return 0
            res = int(i == finish)
            for j, v in enumerate(locations):
                if j != i:
                    if (cost := abs(locations[i] - v)) <= t:
                        res += dfs(j, t - cost)
            return res % mod

        mod = 10**9 + 7
        return dfs(start, fuel)
