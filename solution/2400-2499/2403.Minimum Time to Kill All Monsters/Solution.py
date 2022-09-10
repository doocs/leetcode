class Solution:
    def minimumTime(self, power: List[int]) -> int:
        @cache
        def dfs(mask):
            cnt = mask.bit_count()
            if cnt == len(power):
                return 0
            ans = inf
            for i, v in enumerate(power):
                if mask & (1 << i):
                    continue
                ans = min(ans, dfs(mask | 1 << i) + (v + cnt) // (cnt + 1))
            return ans

        return dfs(0)
