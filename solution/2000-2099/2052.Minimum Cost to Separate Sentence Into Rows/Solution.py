class Solution:
    def minimumCost(self, sentence: str, k: int) -> int:
        @cache
        def dfs(i):
            if s[-1] - s[i] + n - i - 1 <= k:
                return 0
            ans, j = inf, i + 1
            while j < n and (t := s[j] - s[i] + j - i - 1) <= k:
                ans = min(ans, (k - t) ** 2 + dfs(j))
                j += 1
            return ans

        t = [len(w) for w in sentence.split()]
        n = len(t)
        s = list(accumulate(t, initial=0))
        return dfs(0)
