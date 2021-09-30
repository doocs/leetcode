class Solution:
    def dominantIndex(self, nums: List[int]) -> int:
        max_idx, n = 0, len(nums)
        for i in range(1, n):
            if nums[i] > nums[max_idx]:
                max_idx = i
        for i in range(n):
            if i != max_idx and nums[i] * 2 > nums[max_idx]:
                return -1
        return max_idx
