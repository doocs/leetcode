class Solution:
    def diagonalSort(self, mat: List[List[int]]) -> List[List[int]]:
        m, n = len(mat), len(mat[0])
        for k in range(min(m, n) - 1):
            for i in range(m - 1):
                for j in range(n - 1):
                    if mat[i][j] > mat[i + 1][j + 1]:
                        mat[i][j], mat[i + 1][j + 1] = mat[i + 1][j + 1], mat[i][j]
        return mat
