class Solution:
    def largestSumOfAverages(self, nums: List[int], k: int) -> float:
        @cache
        def dfs(i, k):
            if i == n:
                return 0
            if k == 1:
                return (s[-1] - s[i]) / (n - i)
            ans = 0
            for j in range(i, n):
                t = (s[j + 1] - s[i]) / (j - i + 1) + dfs(j + 1, k - 1)
                ans = max(ans, t)
            return ans

        n = len(nums)
        s = list(accumulate(nums, initial=0))
        return dfs(0, k)
