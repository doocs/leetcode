class Solution:
    def minGroups(self, intervals: List[List[int]]) -> int:
        h = []
        for a, b in sorted(intervals):
            if h and h[0] < a:
                heappop(h)
            heappush(h, b)
        return len(h)
