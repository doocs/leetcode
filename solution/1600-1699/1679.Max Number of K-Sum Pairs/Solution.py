class Solution:
    def maxOperations(self, nums: List[int], k: int) -> int:
        nums.sort()
        ans, l, r = 0, 0, len(nums) - 1
        while l < r:
            if nums[l] + nums[r] > k:
                r -= 1
            elif nums[l] + nums[r] < k:
                l += 1
            else:
                ans += 1
                l += 1
                r -= 1
        return ans
