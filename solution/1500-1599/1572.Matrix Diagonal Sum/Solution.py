class Solution:
    def diagonalSum(self, mat: List[List[int]]) -> int:
        ans = 0
        n = len(mat)
        for i, row in enumerate(mat):
            j = n - i - 1
            ans += row[i] + (0 if j == i else row[j])
        return ans
