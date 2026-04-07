class Solution:
    def minAbsoluteDifference(self, nums: list[int]) -> int:
        n = len(nums)
        ans = n + 1
        last = [-inf] * 3
        for i, x in enumerate(nums):
            if x:
                ans = min(ans, i - last[3 - x])
                last[x] = i
        return -1 if ans > n else ans
