class Solution:
    def kthSmallest(self, matrix: List[List[int]], k: int) -> int:
        def check(matrix, mid, k, n):
            count = 0
            i, j = n - 1, 0
            while i >= 0 and j < n:
                if matrix[i][j] <= mid:
                    count += i + 1
                    j += 1
                else:
                    i -= 1
            return count >= k

        n = len(matrix)
        left, right = matrix[0][0], matrix[n - 1][n - 1]
        while left < right:
            mid = (left + right) >> 1
            if check(matrix, mid, k, n):
                right = mid
            else:
                left = mid + 1
        return left
