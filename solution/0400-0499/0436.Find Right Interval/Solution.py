class Solution:
    def findRightInterval(self, intervals: List[List[int]]) -> List[int]:
        n = len(intervals)
        starts = [(intervals[i][0], i) for i in range(n)]
        starts.sort(key=lambda x : x[0])
        res = []
        for _, end in intervals:
            left, right = 0, n - 1
            while left < right:
                mid = (left + right) >> 1
                if starts[mid][0] >= end:
                    right = mid
                else:
                    left = mid + 1
            res.append(-1 if starts[left][0] < end else starts[left][1])
        return res
