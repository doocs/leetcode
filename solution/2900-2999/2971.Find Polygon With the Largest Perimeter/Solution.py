class Solution:
    def largestPerimeter(self, nums: List[int]) -> int:
        nums.sort()
        s = list(accumulate(nums, initial=0))
        ans = -1
        for k in range(3, len(nums) + 1):
            if s[k - 1] > nums[k - 1]:
                ans = max(ans, s[k])
        return ans
