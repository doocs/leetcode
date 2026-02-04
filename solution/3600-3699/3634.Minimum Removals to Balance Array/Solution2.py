class Solution:
    def minRemoval(self, nums: List[int], k: int) -> int:
        nums.sort()
        ans = n = len(nums)
        r = 0
        for l in range(n):
            while r < n and nums[r] <= nums[l] * k:
                r += 1
            ans = min(ans, n - (r - l))
        return ans
