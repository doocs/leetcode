class Solution:
    def setZeroes(self, matrix):
        """
        :type matrix: List[List[int]]
        :rtype: void Do not return anything, modify matrix in-place instead.
        """

        coord = []
        for i in range(len(matrix)):
            for j in range(len(matrix[i])):
                if matrix[i][j] == 0:
                    coord.append((i, j))
        
        for i, j in coord:
            matrix[i] = [0]*len(matrix[i])
            for x in range(len(matrix)):
                matrix[x][j] = 0
