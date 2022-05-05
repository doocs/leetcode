class Solution:
    def maxOperations(self, nums: List[int], k: int) -> int:
        nums.sort()
        l, r, ans = 0, len(nums) - 1, 0
        while l < r:
            s = nums[l] + nums[r]
            if s == k:
                ans += 1
                l, r = l + 1, r - 1
            elif s > k:
                r -= 1
            else:
                l += 1
        return ans
