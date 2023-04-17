class Solution:
    def countDaysTogether(
        self, arriveAlice: str, leaveAlice: str, arriveBob: str, leaveBob: str
    ) -> int:
        a = max(arriveAlice, arriveBob)
        b = min(leaveAlice, leaveBob)
        days = (31, 28, 31, 30, 31, 30, 31, 31, 30, 31, 30, 31)
        x = sum(days[: int(a[:2]) - 1]) + int(a[3:])
        y = sum(days[: int(b[:2]) - 1]) + int(b[3:])
        return max(y - x + 1, 0)
