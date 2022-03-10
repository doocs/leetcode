class Solution:
    def singleNonDuplicate(self, nums: List[int]) -> int:
        left, right = 0, len(nums) - 1
        while left < right:
            mid = (left + right) >> 1
            # Equals to: if (mid % 2 == 0 and nums[mid] != nums[mid + 1]) or (mid % 2 == 1 and nums[mid] != nums[mid - 1]):
            if nums[mid] != nums[mid ^ 1]:
                right = mid
            else:
                left = mid + 1
        return nums[left]
