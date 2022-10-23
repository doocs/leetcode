class Solution:
    def minTotalDistance(self, grid: List[List[int]]) -> int:
        def f(arr, x):
            return sum(abs(v - x) for v in arr)

        rows, cols = [], []
        for i, row in enumerate(grid):
            for j, v in enumerate(row):
                if v:
                    rows.append(i)
                    cols.append(j)
        cols.sort()
        i = rows[len(rows) >> 1]
        j = cols[len(cols) >> 1]
        return f(rows, i) + f(cols, j)
