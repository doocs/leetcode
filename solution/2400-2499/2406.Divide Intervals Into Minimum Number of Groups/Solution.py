class Solution:
    def minGroups(self, intervals: list[list[int]]) -> int:
        q = []
        for left, right in sorted(intervals):
            if q and q[0] < left:
                heappop(q)
            heappush(q, right)
        return len(q)
