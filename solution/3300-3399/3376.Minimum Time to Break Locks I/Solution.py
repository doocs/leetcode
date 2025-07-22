class Solution:
    def findMinimumTime(self, strength: List[int], K: int) -> int:
        @cache
        def dfs(i: int) -> int:
            if i == (1 << len(strength)) - 1:
                return 0
            cnt = i.bit_count()
            x = 1 + cnt * K
            ans = inf
            for j, s in enumerate(strength):
                if i >> j & 1 ^ 1:
                    ans = min(ans, dfs(i | 1 << j) + (s + x - 1) // x)
            return ans

        return dfs(0)
