class Solution:
    def kClosest(self, points: List[List[int]], k: int) -> List[List[int]]:
        max_q = []
        for i, (x, y) in enumerate(points):
            dist = math.hypot(x, y)
            heappush(max_q, (-dist, i))
            if len(max_q) > k:
                heappop(max_q)
        return [points[i] for _, i in max_q]
