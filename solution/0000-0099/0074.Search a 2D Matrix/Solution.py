class Solution:
    def searchMatrix(self, matrix, target):
        """
        :type matrix: List[List[int]]
        :type target: int
        :rtype: bool
        """

        if not matrix or not matrix[0]:
            return False

        for row in matrix:
            if row[0] <= target <= row[-1]:
                s = 0
                e = len(row)-1
                while s <= e:
                    mid = (s+e)//2
                    if row[mid] == target:
                        return True
                    elif row[mid] > target:
                        e = mid-1
                    else:
                        s = mid+1

                return False

        return False
