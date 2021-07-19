class Solution:
    def kWeakestRows(self, mat: List[List[int]], k: int) -> List[int]:
        m, n = len(mat), len(mat[0])
        res = []
        for row in mat:
            left, right = 0, n
            while left < right:
                mid = (left + right) >> 1
                if row[mid] == 0:
                    right = mid
                else:
                    left = mid + 1
            res.append(left)
        idx = list(range(m))
        idx.sort(key=lambda x: res[x])
        return idx[:k]
