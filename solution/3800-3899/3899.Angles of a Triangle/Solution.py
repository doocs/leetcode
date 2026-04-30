class Solution:
    def internalAngles(self, sides: list[int]) -> list[float]:
        sides.sort()
        a, b, c = sides
        if a + b <= c:
            return []
        A = degrees(acos((b**2 + c**2 - a**2) / (2 * b * c)))
        B = degrees(acos((a**2 + c**2 - b**2) / (2 * a * c)))
        C = 180 - A - B
        return [A, B, C]
