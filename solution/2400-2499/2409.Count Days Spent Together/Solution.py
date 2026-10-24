f = list(accumulate([31, 28, 31, 30, 31, 30, 31, 31, 30, 31, 30, 31], initial=0))


class Solution:
    def countDaysTogether(
        self, arriveAlice: str, leaveAlice: str, arriveBob: str, leaveBob: str
    ) -> int:
        start = max(arriveAlice, arriveBob)
        end = min(leaveAlice, leaveBob)
        d1 = f[int(start[:2]) - 1] + int(start[3:])
        d2 = f[int(end[:2]) - 1] + int(end[3:])
        return max(d2 - d1 + 1, 0)
