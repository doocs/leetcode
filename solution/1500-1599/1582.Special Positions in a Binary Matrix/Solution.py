class Solution:
    def numSpecial(self, mat: List[List[int]]) -> int:
        rows = [0] * len(mat)
        cols = [0] * len(mat[0])
        for i, row in enumerate(mat):
            for j, x in enumerate(row):
                rows[i] += x
                cols[j] += x
        ans = 0
        for i, row in enumerate(mat):
            for j, x in enumerate(row):
                ans += x == 1 and rows[i] == 1 and cols[j] == 1
        return ans
