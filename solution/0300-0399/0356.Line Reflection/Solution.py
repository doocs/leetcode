class Solution:
    def isReflected(self, points: List[List[int]]) -> bool:
        min_x, max_x = float('inf'), float('-inf')
        point_set = set()
        for point in points:
            min_x = min(min_x, point[0])
            max_x = max(max_x, point[0])
            point_set.add((point[0], point[1]))
        s = min_x + max_x
        for point in points:
            if (s - point[0], point[1]) not in point_set:
                return False
        return True
