class Solution:
    def findDegrees(self, matrix: list[list[int]]) -> list[int]:
        ans = [0] * len(matrix)
        for i, row in enumerate(matrix):
            for x in row:
                ans[i] += x
        return ans
