class Solution:
    def numPoints(self, darts: list[list[int]], r: int) -> int:
        def countDarts(x, y):
            count = 0
            for x1, y1 in darts:
                if dist((x, y), (x1, y1)) <= r + 1e-7:
                    count += 1
            return count

        def possibleCenters(x1, y1, x2, y2):
            dx, dy = x2 - x1, y2 - y1
            d = sqrt(dx * dx + dy * dy)
            if d > 2 * r:
                return []
            mid_x, mid_y = (x1 + x2) / 2, (y1 + y2) / 2
            dist_to_center = sqrt(r * r - (d / 2) * (d / 2))
            offset_x = dist_to_center * dy / d
            offset_y = dist_to_center * -dx / d
            return [
                (mid_x + offset_x, mid_y + offset_y),
                (mid_x - offset_x, mid_y - offset_y),
            ]

        n = len(darts)
        max_darts = 1

        for i in range(n):
            for j in range(i + 1, n):
                centers = possibleCenters(
                    darts[i][0], darts[i][1], darts[j][0], darts[j][1]
                )
                for center in centers:
                    max_darts = max(max_darts, countDarts(center[0], center[1]))

        return max_darts
