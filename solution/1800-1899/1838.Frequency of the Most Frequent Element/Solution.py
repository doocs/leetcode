class Solution:
    def maxFrequency(self, nums: List[int], k: int) -> int:
        nums.sort()
        l, r, n = 0, 1, len(nums)
        ans, window = 1, 0
        while r < n:
            window += (nums[r] - nums[r - 1]) * (r - l)
            while window > k:
                window -= nums[r] - nums[l]
                l += 1
            r += 1
            ans = max(ans, r - l)
        return ans
