class Solution:
    def findRightInterval(self, intervals: List[List[int]]) -> List[int]:
        for i, v in enumerate(intervals):
            v.append(i)
        intervals.sort()
        n = len(intervals)
        ans = [-1] * n
        for _, e, i in intervals:
            j = bisect_left(intervals, [e])
            if j < n:
                ans[i] = intervals[j][2]
        return ans
