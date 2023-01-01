class Solution:
    def minimumPartition(self, s: str, k: int) -> int:
        @cache
        def dfs(i):
            if i >= n:
                return 0
            res, v = inf, 0
            for j in range(i, n):
                v = v * 10 + int(s[j])
                if v > k:
                    break
                res = min(res, dfs(j + 1))
            return res + 1

        n = len(s)
        ans = dfs(0)
        return ans if ans < inf else -1
