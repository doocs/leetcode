class Solution:
    def cutSquares(self, square1: List[int], square2: List[int]) -> List[float]:
        x1, y1 = square1[0] + square1[2] / 2, square1[1] + square1[2] / 2
        x2, y2 = square2[0] + square2[2] / 2, square2[1] + square2[2] / 2
        if x1 == x2:
            y3 = min(square1[1], square2[1])
            y4 = max(square1[1] + square1[2], square2[1] + square2[2])
            return [x1, y3, x2, y4]
        k = (y2 - y1) / (x2 - x1)
        b = y1 - k * x1
        if abs(k) > 1:
            y3 = min(square1[1], square2[1])
            x3 = (y3 - b) / k
            y4 = max(square1[1] + square1[2], square2[1] + square2[2])
            x4 = (y4 - b) / k
            if x3 > x4 or (x3 == x4 and y3 > y4):
                x3, y3, x4, y4 = x4, y4, x3, y3
        else:
            x3 = min(square1[0], square2[0])
            y3 = k * x3 + b
            x4 = max(square1[0] + square1[2], square2[0] + square2[2])
            y4 = k * x4 + b
        return [x3, y3, x4, y4]
