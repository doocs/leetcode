class Solution:
    def countSubarrays(self, nums: list[int], minK: int, maxK: int) -> int:
        ans = l = 0
        for r, x in enumerate(nums):
            if x < minK or x > maxK:
                l = r + 1
            else:
                ans += r - l + 1
        return ans
