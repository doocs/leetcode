class Solution:
    def arrayChange(self, nums: list[int], operations: list[list[int]]) -> list[int]:
        d = {x: i for i, x in enumerate(nums)}
        for x, y in operations:
            nums[d[x]] = y
            d[y] = d[x]
        return nums
