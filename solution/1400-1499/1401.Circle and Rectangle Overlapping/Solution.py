class Solution:
    def checkOverlap(
        self,
        radius: int,
        xCenter: int,
        yCenter: int,
        x1: int,
        y1: int,
        x2: int,
        y2: int,
    ) -> bool:
        dx = dy = 0
        if x1 > xCenter:
            dx = xCenter - x1
        elif x2 < xCenter:
            dx = xCenter - x2
        if y1 > yCenter:
            dy = yCenter - y1
        elif y2 < yCenter:
            dy = yCenter - y2
        return dx * dx + dy * dy <= radius * radius
