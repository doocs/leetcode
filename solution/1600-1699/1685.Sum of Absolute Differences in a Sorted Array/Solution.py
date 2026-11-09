class Solution:
    def getSumAbsoluteDifferences(self, nums: list[int]) -> list[int]:
        ans = []
        s, t = sum(nums), 0
        for i, x in enumerate(nums):
            v = x * i - t + s - t - x * (len(nums) - i)
            ans.append(v)
            t += x
        return ans
