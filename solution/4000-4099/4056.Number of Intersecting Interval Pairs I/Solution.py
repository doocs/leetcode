class Solution:
    def countIntersectingIntervals(self, intervals: list[list[int]]) -> int:
        n = len(intervals)
        starts = sorted(s for s, _ in intervals)
        ends = sorted(e for _, e in intervals)
        ans = n * (n - 1) // 2
        i = 0
        for start in starts:
            while i < n and ends[i] < start:
                i += 1
            ans -= i
        return ans
