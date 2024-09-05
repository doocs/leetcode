class Solution:
    def decompressRLElist(self, nums: List[int]) -> List[int]:
        return [nums[i + 1] for i in range(0, len(nums), 2) for _ in range(nums[i])]
