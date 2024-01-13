class Solution:
    def numberOfPoints(self, nums: List[List[int]]) -> int:
        d = [0] * 110
        for a, b in nums:
            d[a] += 1
            d[b + 1] -= 1
        return sum(s > 0 for s in accumulate(d))
