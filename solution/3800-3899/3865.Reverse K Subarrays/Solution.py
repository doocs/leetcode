class Solution:
    def reverseSubarrays(self, nums: list[int], k: int) -> list[int]:
        n = len(nums)
        m = n // k
        for i in range(0, n, m):
            nums[i : i + m] = nums[i : i + m][::-1]
        return nums
