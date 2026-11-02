class Solution:
    def getMaxMatrix(self, matrix: List[List[int]]) -> List[int]:
        m, n = len(matrix), len(matrix[0])
        s = [[0] * n for _ in range(m + 1)]
        for i in range(m):
            for j in range(n):
                s[i + 1][j] = s[i][j] + matrix[i][j]

        mx = matrix[0][0]
        ans = [0, 0, 0, 0]
        for i1 in range(m):
            for i2 in range(i1, m):
                nums = [0] * n
                for j in range(n):
                    nums[j] = s[i2 + 1][j] - s[i1][j]

                start = 0
                f = nums[0]
                for j in range(1, n):
                    if f > 0:
                        f += nums[j]
                    else:
                        f = nums[j]
                        start = j
                    if f > mx:
                        mx = f
                        ans = [i1, start, i2, j]
        return ans
