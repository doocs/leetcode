class Solution:
    def missingNumber(self, nums: List[int]) -> int:
        l, r = 0, len(nums) - 1
        if r == 0 or nums[0] == 1:
            return nums[0] ^ 1
        if nums[r] == r:
            return r + 1
        while r - l > 1:
            m = (l + r) >> 1
            if nums[m] == m:
                l = m
            else:
                r = m
        return nums[r] - 1
