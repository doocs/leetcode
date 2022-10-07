class Solution:
    def largestSubarray(self, nums: List[int], k: int) -> List[int]:
        mx = max(nums[: len(nums) - k + 1])
        i = nums.index(mx)
        return nums[i : i + k]
