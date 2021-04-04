class Solution:
    def permute(self, nums: List[int]) -> List[List[int]]:
        if len(nums) <= 1:
            return [nums]
        res = []
        for i, num in enumerate(nums):
            n = nums[:i] + nums[i + 1:]
            for item in self.permute(n):
                res.append([num] + item)
        return res
