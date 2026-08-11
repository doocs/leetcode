class Solution:
    def missingInteger(self, nums: List[int]) -> int:
        s = nums[0]
        for x, y in pairwise(nums):
            if x + 1 != y:
                break
            s += y
        st = set(nums)
        while s in st:
            s += 1
        return s
