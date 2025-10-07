class Solution:
    def splitArray(self, nums: List[int]) -> int:
        s = list(accumulate(nums))
        n = len(nums)
        f = [True] * n
        for i in range(1, n):
            f[i] = f[i - 1]
            if nums[i] <= nums[i - 1]:
                f[i] = False
        g = [True] * n
        for i in range(n - 2, -1, -1):
            g[i] = g[i + 1]
            if nums[i] <= nums[i + 1]:
                g[i] = False
        ans = inf
        for i in range(n - 1):
            if f[i] and g[i + 1]:
                s1 = s[i]
                s2 = s[n - 1] - s[i]
                ans = min(ans, abs(s1 - s2))
        return ans if ans < inf else -1
