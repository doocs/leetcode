class Solution:
    def oddCells(self, m: int, n: int, indices: List[List[int]]) -> int:
        row = [0] * m
        col = [0] * n
        for r, c in indices:
            row[r] += 1
            col[c] += 1
        cnt1 = sum(v % 2 for v in row)
        cnt2 = sum(v % 2 for v in col)
        return cnt1 * (n - cnt2) + cnt2 * (m - cnt1)
