class Solution:
    def search(self, nums: List[int], target: int) -> int:
        if len(nums) == 0:
            return 0
        left, right = 0, len(nums) - 1
        while left < right:
            mid = (left + right) >> 1
            if nums[mid] >= target:
                right = mid
            else:
                left = mid + 1
        if nums[left] != target:
            return 0
        l, right = left, len(nums) - 1
        while left < right:
            mid = (left + right + 1) >> 1
            if nums[mid] <= target:
                left = mid
            else:
                right = mid - 1
        return left - l + 1
