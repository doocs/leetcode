class Solution:
    def countRectangles(
        self, rectangles: List[List[int]], points: List[List[int]]
    ) -> List[int]:
        d = defaultdict(list)
        for x, y in rectangles:
            d[y].append(x)
        for y in d.keys():
            d[y].sort()
        ans = []
        for x, y in points:
            cnt = 0
            for h in range(y, 101):
                xs = d[h]
                cnt += len(xs) - bisect_left(xs, x)
            ans.append(cnt)
        return ans
