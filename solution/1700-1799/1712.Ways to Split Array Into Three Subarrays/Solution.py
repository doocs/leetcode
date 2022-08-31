class Solution:
    def waysToSplit(self, nums: List[int]) -> int:
        mod = 10**9 + 7
        s = list(accumulate(nums))
        ans, n = 0, len(nums)
        for i in range(n - 2):
            j0 = bisect_left(s, s[i] * 2, i + 1, n - 1)
            j1 = bisect_right(s, (s[-1] + s[i]) // 2, j0, n - 1)
            ans += j1 - j0
        return ans % mod
