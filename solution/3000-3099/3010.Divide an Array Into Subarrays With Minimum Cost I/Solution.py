class Solution:
    def minimumCost(self, nums: List[int]) -> int:
        a, b, c = nums[0], inf, inf
        for x in nums[1:]:
            if x < b:
                c, b = b, x
            elif x < c:
                c = x
        return a + b + c
