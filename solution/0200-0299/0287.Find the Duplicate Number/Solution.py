class Solution:
    def findDuplicate(self, nums: List[int]) -> int:
        left, right = 1, len(nums) - 1
        while left < right:
            mid = (left + right) >> 1
            cnt = sum(v <= mid for v in nums)
            if cnt > mid:
                right = mid
            else:
                left = mid + 1
        return left
