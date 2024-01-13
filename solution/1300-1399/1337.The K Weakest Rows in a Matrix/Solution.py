class Solution:
    def kWeakestRows(self, mat: List[List[int]], k: int) -> List[int]:
        m, n = len(mat), len(mat[0])
        ans = [n - bisect_right(row[::-1], 0) for row in mat]
        idx = list(range(m))
        idx.sort(key=lambda i: ans[i])
        return idx[:k]
