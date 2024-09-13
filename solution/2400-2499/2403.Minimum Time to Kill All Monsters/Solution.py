class Solution:
    def minimumTime(self, power: List[int]) -> int:
        @cache
        def dfs(mask: int) -> int:
            if mask == 0:
                return 0
            ans = inf
            gain = 1 + (n - mask.bit_count())
            for i, x in enumerate(power):
                if mask >> i & 1:
                    ans = min(ans, dfs(mask ^ (1 << i)) + (x + gain - 1) // gain)
            return ans

        n = len(power)
        return dfs((1 << n) - 1)
