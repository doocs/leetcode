class Solution:
    def minimumOperations(self, num: str) -> int:
        @cache
        def dfs(i: int, k: int) -> int:
            if i == n:
                return 0 if k == 0 else n
            ans = dfs(i + 1, k) + 1
            ans = min(ans, dfs(i + 1, (k * 10 + int(num[i])) % 25))
            return ans

        n = len(num)
        return dfs(0, 0)
