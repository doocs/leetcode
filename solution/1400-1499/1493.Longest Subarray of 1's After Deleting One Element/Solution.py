class Solution:
    def longestSubarray(self, nums: List[int]) -> int:
        n = len(nums)
        left = [0] * n
        right = [0] * n
        for i in range(1, n):
            if nums[i - 1] == 1:
                left[i] = left[i - 1] + 1
        for i in range(n - 2, -1, -1):
            if nums[i + 1] == 1:
                right[i] = right[i + 1] + 1
        return max(a + b for a, b in zip(left, right))
