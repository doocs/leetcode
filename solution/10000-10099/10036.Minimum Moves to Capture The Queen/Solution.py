class Solution:
    def minMovesToCaptureTheQueen(
        self, a: int, b: int, c: int, d: int, e: int, f: int
    ) -> int:
        def check(dirs, sx, sy, bx, by) -> bool:
            for dx, dy in pairwise(dirs):
                for k in range(1, 8):
                    x = sx + dx * k
                    y = sy + dy * k
                    if not (1 <= x <= 8 and 1 <= y <= 8) or (x, y) == (bx, by):
                        break
                    if (x, y) == (e, f):
                        return True
            return False

        dirs1 = (-1, 0, 1, 0, -1)
        dirs2 = (-1, 1, 1, -1, -1)
        return 1 if check(dirs1, a, b, c, d) or check(dirs2, c, d, a, b) else 2
