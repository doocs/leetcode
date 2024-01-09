class Solution:
    def missingInteger(self, nums: List[int]) -> int:
        s, j = nums[0], 1
        while j < len(nums) and nums[j] == nums[j - 1] + 1:
            s += nums[j]
            j += 1
        vis = set(nums)
        for x in count(s):
            if x not in vis:
                return x
