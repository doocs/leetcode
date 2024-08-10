import math
from collections import defaultdict

class Solution:
    def numPoints(self, darts: list[list[int]], r: int) -> int:
        def countDarts(x, y):
            count = 0
            for dart in darts:
                if math.dist((x, y), (dart[0], dart[1])) <= r + 1e-7:  # Add small epsilon for precision issues
                    count += 1
            return count
        
        def possibleCenters(x1, y1, x2, y2):
            dx, dy = x2 - x1, y2 - y1
            d = math.sqrt(dx * dx + dy * dy)
            if d > 2 * r:
                return []
            mid_x, mid_y = (x1 + x2) / 2, (y1 + y2) / 2
            dist_to_center = math.sqrt(r * r - (d / 2) * (d / 2))
            offset_x = dist_to_center * dy / d
            offset_y = dist_to_center * -dx / d
            return [(mid_x + offset_x, mid_y + offset_y), (mid_x - offset_x, mid_y - offset_y)]

        n = len(darts)
        max_darts = 1
        
        for i in range(n):
            for j in range(i + 1, n):
                centers = possibleCenters(darts[i][0], darts[i][1], darts[j][0], darts[j][1])
                for center in centers:
                    max_darts = max(max_darts, countDarts(center[0], center[1]))

        return max_darts

# Example usage:
solution = Solution()
print(solution.numPoints([[-2,0],[2,0],[0,2],[0,-2]], 2))  # Output: 4
print(solution.numPoints([[-3,0],[3,0],[2,6],[5,4],[0,9],[7,8]], 5))  # Output: 5
