class Solution:
    def countKDifference(self, nums: list[int], k: int) -> int:
        n = len(nums)
        return sum(
            abs(nums[i] - nums[j]) == k for i in range(n) for j in range(i + 1, n)
        )
