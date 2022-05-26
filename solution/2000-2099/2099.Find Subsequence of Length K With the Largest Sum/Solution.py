class Solution:
    def maxSubsequence(self, nums: List[int], k: int) -> List[int]:
        idx = list(range(len(nums)))
        idx.sort(key=lambda i: nums[i])
        return [nums[i] for i in sorted(idx[-k:])]
