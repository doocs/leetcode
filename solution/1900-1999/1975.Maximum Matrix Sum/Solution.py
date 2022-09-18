class Solution:
    def maxMatrixSum(self, matrix: List[List[int]]) -> int:
        s = cnt = 0
        mi = inf
        for row in matrix:
            for v in row:
                s += abs(v)
                mi = min(mi, abs(v))
                if v < 0:
                    cnt += 1
        if cnt % 2 == 0 or mi == 0:
            return s
        return s - mi * 2
