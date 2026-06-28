class Solution:
    def filterOccupiedIntervals(
        self, occupiedIntervals: List[List[int]], freeStart: int, freeEnd: int
    ) -> List[List[int]]:
        occupiedIntervals.sort(key=lambda x: x[0])
        busy = [occupiedIntervals[0]]
        for interval in occupiedIntervals[1:]:
            if busy[-1][1] + 1 < interval[0]:
                busy.append(interval)
            else:
                busy[-1][1] = max(busy[-1][1], interval[1])
        ans = []
        for interval in busy:
            if interval[1] < freeStart or freeEnd < interval[0]:
                ans.append(interval)
            else:
                if interval[0] < freeStart:
                    ans.append([interval[0], freeStart - 1])
                if interval[1] > freeEnd:
                    ans.append([freeEnd + 1, interval[1]])
        return ans
