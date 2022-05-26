class Solution:
    def maxEqualRowsAfterFlips(self, matrix: List[List[int]]) -> int:
        cnt = Counter()
        for row in matrix:
            t = []
            for v in row:
                if row[0] == 1:
                    v ^= 1
                t.append(str(v))
            s = ''.join(t)
            cnt[s] += 1
        return max(cnt.values())
