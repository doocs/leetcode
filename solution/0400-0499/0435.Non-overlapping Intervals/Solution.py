class Solution:
    def eraseOverlapIntervals(self, intervals: List[List[int]]) -> int:
        if not intervals:
            return 0
        intervals.sort(key=lambda x: x[1])
        cnt, end = 0, intervals[0][1]
        for interval in intervals[1:]:
            if interval[0] >= end:
                end = interval[1]
            else:
                cnt += 1
        return cnt
