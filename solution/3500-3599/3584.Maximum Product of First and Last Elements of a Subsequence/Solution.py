class Solution:
    def maximumProduct(self, nums: List[int], m: int) -> int:
        ans = mx = -inf
        mi = inf
        for i in range(m - 1, len(nums)):
            x = nums[i]
            y = nums[i - m + 1]
            mi = min(mi, y)
            mx = max(mx, y)
            ans = max(ans, x * mi, x * mx)
        return ans
