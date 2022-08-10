class Solution:
    def twoSum(self, nums: List[int], target: int) -> List[int]:
        m = {}
        for i, v in enumerate(nums):
            x = target - v
            if x in m:
                return [m[x], i]
            m[v] = i
