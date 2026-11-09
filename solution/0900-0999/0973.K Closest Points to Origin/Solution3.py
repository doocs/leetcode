class Solution:
    def kClosest(self, points: list[list[int]], k: int) -> list[list[int]]:
        dist = [x * x + y * y for x, y in points]
        l, r = 0, max(dist)
        while l < r:
            mid = (l + r) >> 1
            cnt = sum(d <= mid for d in dist)
            if cnt >= k:
                r = mid
            else:
                l = mid + 1
        return [points[i] for i, d in enumerate(dist) if d <= l]
