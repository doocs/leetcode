class Solution:
    def isMajorityElement(self, nums: List[int], target: int) -> bool:
        def bsearch_left(nums, target, left, right):
            while left < right:
                mid = (left + right) >> 1
                if nums[mid] >= target:
                    right = mid
                else:
                    left = mid + 1
            return left if nums[left] == target else -1
        
        def bsearch_right(nums, target, left, right):
            while left < right:
                mid = (left + right + 1) >> 1
                if nums[mid] <= target:
                    left = mid
                else:
                    right = mid - 1
            return left if nums[left] == target else -1

        n = len(nums)
        left = bsearch_left(nums, target, 0, n - 1)
        if left == -1:
            return False
        right = bsearch_right(nums, target, left, n - 1)
        if right == -1:
            return False
        return right - left + 1 > n >> 1
