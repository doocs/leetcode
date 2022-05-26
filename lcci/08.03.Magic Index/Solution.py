class Solution:
    def findMagicIndex(self, nums: List[int]) -> int:
        def find(nums, left, right):
            if left > right:
                return -1
            mid = (left + right) >> 1
            left_index = find(nums, left, mid - 1)
            if left_index != -1:
                return left_index
            if nums[mid] == mid:
                return mid
            return find(nums, mid + 1, right)

        return find(nums, 0, len(nums) - 1)
