class Solution:
    def getMinDistance(self, nums: List[int], target: int, start: int) -> int:
        ans = inf
        for i, x in enumerate(nums):
            if x == target:
                ans = min(ans, abs(i - start))
        return ans
