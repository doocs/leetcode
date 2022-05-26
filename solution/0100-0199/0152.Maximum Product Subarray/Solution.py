class Solution:
    def maxProduct(self, nums: List[int]) -> int:
        maxf = minf = nums[0]
        res, n = nums[0], len(nums)
        for i in range(1, n):
            p, q = maxf, minf
            maxf = max(nums[i], p * nums[i], q * nums[i])
            minf = min(nums[i], p * nums[i], q * nums[i])
            res = max(res, maxf)
        return res
