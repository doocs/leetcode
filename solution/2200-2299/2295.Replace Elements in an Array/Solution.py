class Solution:
    def arrayChange(self, nums: List[int], operations: List[List[int]]) -> List[int]:
        d = {v: i for i, v in enumerate(nums)}
        for a, b in operations:
            nums[d[a]] = b
            d[b] = d[a]
        return nums
