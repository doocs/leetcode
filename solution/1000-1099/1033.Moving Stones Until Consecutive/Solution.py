class Solution:
    def numMovesStones(self, a: int, b: int, c: int) -> List[int]:
        x, z = min(a, b, c), max(a, b, c)
        y = a + b + c - x - z
        mi = mx = 0
        if z - x > 2:
            mi = 1 if y - x < 3 or z - y < 3 else 2
            mx = z - x - 2
        return [mi, mx]
