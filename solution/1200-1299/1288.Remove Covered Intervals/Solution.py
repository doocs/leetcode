class Solution:
    def removeCoveredIntervals(self, intervals: List[List[int]]) -> int:
        intervals.sort(key=lambda x: (x[0], -x[1]))
        cnt, pre = 1, intervals[0]
        for e in intervals[1:]:
            if pre[1] < e[1]:
                cnt += 1
                pre = e
        return cnt
