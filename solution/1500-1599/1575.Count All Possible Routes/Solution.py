class Solution:
    def countRoutes(
        self, locations: List[int], start: int, finish: int, fuel: int
    ) -> int:
        @cache
        def dfs(i: int, k: int) -> int:
            if k < 0 or abs(locations[i] - locations[finish] > k):
                return 0
            ans = int(i == finish)
            ans += sum(
                dfs(j, k - abs(locations[i] - x))
                for j, x in enumerate(locations)
                if j != i
            )
            return ans % mod

        mod = 10**9 + 7
        return dfs(start, fuel)
