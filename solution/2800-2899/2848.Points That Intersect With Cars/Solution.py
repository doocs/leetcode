class Solution:
    def numberOfPoints(self, nums: List[List[int]]) -> int:
        m = 102
        d = [0] * m
        for start, end in nums:
            d[start] += 1
            d[end + 1] -= 1
        return sum(s > 0 for s in accumulate(d))
