class Solution:
    def findRotation(self, mat: List[List[int]], target: List[List[int]]) -> bool:
        n = len(mat)
        ok = 0b1111
        for i in range(n):
            for j in range(n):
                if mat[i][j] != target[i][j]:
                    ok &= ~0b0001
                if mat[j][n - 1 - i] != target[i][j]:
                    ok &= ~0b0010
                if mat[n - 1 - i][n - 1 - j] != target[i][j]:
                    ok &= ~0b0100
                if mat[n - 1 - j][i] != target[i][j]:
                    ok &= ~0b1000
                if ok == 0:
                    return False
        return ok != 0
