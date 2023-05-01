class Solution:
    def countRoutes(
        self, locations: List[int], start: int, finish: int, fuel: int
    ) -> int:
        @cache
        def dfs(i: int, k: int) -> int:
            if k < abs(locations[i] - locations[finish]):
                return 0
            ans = int(i == finish)
            for j, x in enumerate(locations):
                if j != i:
                    ans = (ans + dfs(j, k - abs(locations[i] - x))) % mod
            return ans

        mod = 10**9 + 7
        return dfs(start, fuel)
