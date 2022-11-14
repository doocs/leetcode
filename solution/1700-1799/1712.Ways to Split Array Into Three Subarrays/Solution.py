class Solution:
    def waysToSplit(self, nums: List[int]) -> int:
        mod = 10**9 + 7
        s = list(accumulate(nums))
        ans, n = 0, len(nums)
        for i in range(n - 2):
            j = bisect_left(s, s[i] << 1, i + 1, n - 1)
            k = bisect_right(s, (s[-1] + s[i]) >> 1, j, n - 1)
            ans += k - j
        return ans % mod
