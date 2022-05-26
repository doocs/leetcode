class Solution:
    def getAverages(self, nums: List[int], k: int) -> List[int]:
        n = len(nums)
        presum = [0] * (n + 1)
        for i in range(n):
            presum[i + 1] = presum[i] + nums[i]
        return [
            -1
            if i - k < 0 or i + k >= n
            else (presum[i + k + 1] - presum[i - k]) // (k * 2 + 1)
            for i in range(n)
        ]
