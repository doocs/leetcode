class Solution:
    def dayOfYear(self, date: str) -> int:
        y, m, d = (int(s) for s in date.split('-'))
        v = 29 if y % 400 == 0 or (y % 4 == 0 and y % 100) else 28
        days = [31, v, 31, 30, 31, 30, 31, 31, 30, 31, 30, 31]
        return sum(days[: m - 1]) + d
