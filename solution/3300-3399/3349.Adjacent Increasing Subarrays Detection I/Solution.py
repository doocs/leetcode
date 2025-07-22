class Solution:
    def hasIncreasingSubarrays(self, nums: List[int], k: int) -> bool:
        mx = pre = cur = 0
        for i, x in enumerate(nums):
            cur += 1
            if i == len(nums) - 1 or x >= nums[i + 1]:
                mx = max(mx, cur // 2, min(pre, cur))
                pre, cur = cur, 0
        return mx >= k
