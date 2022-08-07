class Solution:
    def minimumAverageDifference(self, nums: List[int]) -> int:
        s = list(accumulate(nums))
        ans, n = 0, len(nums)
        mi = inf
        for i in range(n):
            a = s[i] // (i + 1)
            b = 0 if i == n - 1 else (s[-1] - s[i]) // (n - i - 1)
            t = abs(a - b)
            if mi > t:
                ans = i
                mi = t
        return ans
