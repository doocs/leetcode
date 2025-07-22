class Solution:
    def removeCoveredIntervals(self, intervals: List[List[int]]) -> int:
        intervals.sort(key=lambda x: (x[0], -x[1]))
        ans = 0
        pre = -inf
        for _, cur in intervals:
            if cur > pre:
                ans += 1
                pre = cur
        return ans
