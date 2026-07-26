class Solution:
    def aggregateTimeSeries(
        self, series1: list[list[int]], series2: list[list[int]]
    ) -> list[list[int]]:
        m, n = len(series1), len(series2)
        i = j = 0
        ans = []
        while i < m and j < n:
            t1, v1 = series1[i]
            t2, v2 = series2[j]
            if t1 == t2:
                ans.append([t1, v1 + v2])
                i += 1
                j += 1
            elif t1 < t2:
                ans.append([t1, v1 + v2])
                i += 1
            else:
                ans.append([t2, v1 + v2])
                j += 1
        while i < m:
            ans.append(series1[i])
            i += 1
        while j < n:
            ans.append(series2[j])
            j += 1
        return ans
