class Solution:
    def secondsBetweenTimes(self, startTime: str, endTime: str) -> int:
        def f(s: str) -> int:
            return int(s[:2]) * 3600 + int(s[3:5]) * 60 + int(s[6:])

        return f(endTime) - f(startTime)
