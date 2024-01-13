class Solution:
    def eraseOverlapIntervals(self, intervals: List[List[int]]) -> int:
        intervals.sort()
        d = [intervals[0][1]]
        for s, e in intervals[1:]:
            if s >= d[-1]:
                d.append(e)
            else:
                idx = bisect_left(d, s)
                d[idx] = min(d[idx], e)
        return len(intervals) - len(d)
