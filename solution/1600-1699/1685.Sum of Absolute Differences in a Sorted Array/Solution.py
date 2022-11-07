class Solution:
    def getSumAbsoluteDifferences(self, nums: List[int]) -> List[int]:
        s = sum(nums)
        t, n = 0, len(nums)
        ans = []
        for i, v in enumerate(nums):
            x = s - t - (n - i) * v + v * i - t
            t += v
            ans.append(x)
        return ans
