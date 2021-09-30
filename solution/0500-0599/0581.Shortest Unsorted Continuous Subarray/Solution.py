class Solution:
    def findUnsortedSubarray(self, nums: List[int]) -> int:
        n = len(nums)
        numsSorted = sorted(nums)
        left, right = 0, n - 1
        while left < n and nums[left] == numsSorted[left]:
            left += 1
        while right >= 0 and nums[right] == numsSorted[right]:
            right -= 1
        return 0 if right == -1 else right - left + 1
