class Solution:
    def minimumDistance(self, points: List[List[int]]) -> int:
        sl1 = SortedList()
        sl2 = SortedList()
        for x, y in points:
            sl1.add(x + y)
            sl2.add(x - y)
        ans = inf
        for x, y in points:
            sl1.remove(x + y)
            sl2.remove(x - y)
            ans = min(ans, max(sl1[-1] - sl1[0], sl2[-1] - sl2[0]))
            sl1.add(x + y)
            sl2.add(x - y)
        return ans
