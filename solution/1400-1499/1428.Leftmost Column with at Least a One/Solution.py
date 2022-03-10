# """
# This is BinaryMatrix's API interface.
# You should not implement it, or speculate about its implementation
# """
# class BinaryMatrix(object):
#    def get(self, row: int, col: int) -> int:
#    def dimensions(self) -> list[]:


class Solution:
    def leftMostColumnWithOne(self, binaryMatrix: 'BinaryMatrix') -> int:
        rows, cols = binaryMatrix.dimensions()
        res = -1
        for row in range(rows):
            left, right = 0, cols - 1
            while left < right:
                mid = (left + right) >> 1
                if binaryMatrix.get(row, mid) == 1:
                    right = mid
                else:
                    left = mid + 1
            if binaryMatrix.get(row, left) == 1:
                if res == -1:
                    res = left
                else:
                    res = min(res, left)
        return res
