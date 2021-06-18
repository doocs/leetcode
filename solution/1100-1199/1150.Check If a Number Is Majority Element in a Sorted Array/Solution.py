class Solution:
    def isMajorityElement(self, nums: List[int], target: int) -> bool:
        left, right = bisect.bisect_left(nums, target), bisect.bisect_right(nums, target)
        return right - left > (len(nums) >> 1)
