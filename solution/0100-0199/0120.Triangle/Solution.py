class Solution:
    def minimumTotal(self, triangle: List[List[int]]) -> int:
        n = len(triangle)
        for i in range(1, n):
            for j in range(i + 1):
                mi = float('inf')
                if j > 0:
                    mi = min(mi, triangle[i - 1][j - 1])
                if j < i:
                    mi = min(mi, triangle[i - 1][j])
                triangle[i][j] += mi
        return min(triangle[n - 1])
