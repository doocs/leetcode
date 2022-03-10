class Solution:
    def numberOfArrays(self, differences: List[int], lower: int, upper: int) -> int:
        num = mi = mx = 0
        for d in differences:
            num += d
            mi = min(mi, num)
            mx = max(mx, num)
        return max(0, upper - lower - (mx - mi) + 1)
