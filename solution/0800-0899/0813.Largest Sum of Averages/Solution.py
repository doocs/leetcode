class Solution:
    def largestSumOfAverages(self, nums: List[int], k: int) -> float:
        @cache
        def dfs(i: int, k: int) -> float:
            if i == n:
                return 0
            if k == 1:
                return (s[n] - s[i]) / (n - i)
            ans = 0
            for j in range(i + 1, n):
                ans = max(ans, (s[j] - s[i]) / (j - i) + dfs(j, k - 1))
            return ans

        n = len(nums)
        s = list(accumulate(nums, initial=0))
        return dfs(0, k)
