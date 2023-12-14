class Solution:
    def findPeakGrid(self, mat: List[List[int]]) -> List[int]:
        l, r = 0, len(mat) - 1
        while l < r:
            mid = (l + r) >> 1
            j = mat[mid].index(max(mat[mid]))
            if mat[mid][j] > mat[mid + 1][j]:
                r = mid
            else:
                l = mid + 1
        return [l, mat[l].index(max(mat[l]))]
