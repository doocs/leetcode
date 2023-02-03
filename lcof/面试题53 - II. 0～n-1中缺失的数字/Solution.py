class Solution:
    def missingNumber(self, nums: List[int]) -> int:
        l, r = 0, len(nums)
        while l < r:
            mid = (l + r) >> 1
            if nums[mid] > mid:
                r = mid
            else:
                l = mid + 1
        return l
