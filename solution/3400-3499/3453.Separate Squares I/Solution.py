class Solution:
    def separateSquares(self, squares: List[List[int]]) -> float:
        def check(y1: float) -> bool:
            t = 0
            for _, y, l in squares:
                if y < y1:
                    t += l * min(y1 - y, l)
            return t >= s / 2

        s = sum(a[2] * a[2] for a in squares)
        l, r = 0, max(a[1] + a[2] for a in squares)
        eps = 1e-5
        while r - l > eps:
            mid = (l + r) / 2
            if check(mid):
                r = mid
            else:
                l = mid
        return r
