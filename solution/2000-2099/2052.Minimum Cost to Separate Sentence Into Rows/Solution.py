class Solution:
    def minimumCost(self, sentence: str, k: int) -> int:
        @cache
        def dfs(i: int) -> int:
            if s[n] - s[i] + n - i - 1 <= k:
                return 0
            ans = inf
            j = i + 1
            while j < n and (m := s[j] - s[i] + j - i - 1) <= k:
                ans = min(ans, dfs(j) + (k - m) ** 2)
                j += 1
            return ans

        nums = [len(s) for s in sentence.split()]
        n = len(nums)
        s = list(accumulate(nums, initial=0))
        return dfs(0)
