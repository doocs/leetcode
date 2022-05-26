class Solution:
    def findMinDifference(self, timePoints: List[str]) -> int:
        if len(timePoints) > 24 * 60:
            return 0
        mins = sorted(int(t[:2]) * 60 + int(t[3:]) for t in timePoints)
        mins.append(mins[0] + 24 * 60)
        res = mins[-1]
        for i in range(1, len(mins)):
            res = min(res, mins[i] - mins[i - 1])
        return res
