class Solution:
    def dayOfYear(self, date: str) -> int:
        year, month, day = (int(e) for e in date.split('-'))
        d = 29 if year % 400 == 0 or (year % 4 == 0 and year % 100 != 0) else 28
        days = [31, d, 31, 30, 31, 30, 31, 31, 30, 31, 30, 31]
        return sum(days[: month - 1]) + day
