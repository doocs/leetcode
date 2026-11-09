class Solution:
    def countPairs(self, nums1: list[int], nums2: list[int]) -> int:
        nums = [a - b for a, b in zip(nums1, nums2)]
        nums.sort()
        l, r = 0, len(nums) - 1
        ans = 0
        while l < r:
            while l < r and nums[l] + nums[r] <= 0:
                l += 1
            ans += r - l
            r -= 1
        return ans
