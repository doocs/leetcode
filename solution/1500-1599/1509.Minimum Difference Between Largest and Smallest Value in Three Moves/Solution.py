class Solution:
    def minDifference(self, nums: List[int]) -> int:
        n = len(nums)
        if n < 5:
            return 0
        nums.sort()
        ans = inf
        for l in range(4):
            r = 3 - l
            ans = min(ans, nums[n - 1 - r] - nums[l])
        return ans
