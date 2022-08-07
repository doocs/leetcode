class Solution:
    def isReflected(self, points: List[List[int]]) -> bool:
        min_x, max_x = inf, -inf
        point_set = set()
        for x, y in points:
            min_x = min(min_x, x)
            max_x = max(max_x, x)
            point_set.add((x, y))
        s = min_x + max_x
        for x, y in points:
            if (s - x, y) not in point_set:
                return False
        return True
