class Solution:
    def isZeroArray(self, nums: List[int], queries: List[List[int]]) -> bool:
        d = [0] * (len(nums) + 1)
        for l, r in queries:
            d[l] += 1
            d[r + 1] -= 1
        s = 0
        for x, y in zip(nums, d):
            s += y
            if x > s:
                return False
        return True
