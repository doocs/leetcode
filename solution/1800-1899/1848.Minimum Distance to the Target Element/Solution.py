class Solution:
    def getMinDistance(self, nums: List[int], target: int, start: int) -> int:
        res = float('inf')
        for i, num in enumerate(nums):
            if num == target:
                res = min(res, abs(i - start))
        return res
