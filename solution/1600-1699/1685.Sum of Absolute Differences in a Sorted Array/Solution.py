class Solution:
    def getSumAbsoluteDifferences(self, nums: List[int]) -> List[int]:
        n = len(nums)
        presum = [0] * (n + 1)
        for i in range(n):
            presum[i + 1] = presum[i] + nums[i]
        res = []
        for i, num in enumerate(nums):
            t = num * i - presum[i] + presum[n] - presum[i + 1] - num * (n - i - 1)
            res.append(t)
        return res
