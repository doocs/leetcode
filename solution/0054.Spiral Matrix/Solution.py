class Solution:
    def rotateCCW(self, Matrix):
        """
        :type Matrix: list[list[int]]
        :rtype Mat2: list[list[int]]
        
        This function IGNORES THE 1st ROW of 
        the Matrix & rotates remaining elements 
        in CounterClockWise direction
        
        Example:
        if Matrix = [ [0, 1, 2, 3],
                      [4, 5, 6, 7],
                      [8, 9, 10, 11]]
        
        then Mat2 = [ [7, 11],
                      [6, 10],
                      [5, 9],
                      [4, 8]]
        """
        
        M = len(Matrix)
        N = len(Matrix[0])
        Mat2 = [[0]*(M-1) for x in range(N)]
        
        for i in range(N-1, -1, -1):
            for j in range(1, M):
                Mat2[N-1-i][j-1] = Matrix[j][i]
            
        return Mat2
            
    def spiralOrder(self, Matrix):
        """
        :type Matrix: list[list[int]]
        :rtype Result: list[int]
        """
        
        Result = []
        while len(Matrix) > 0:
            for i in Matrix[0]:
                Result.append(i)
        
            Matrix = self.rotateCCW(Matrix)
        
        return Result
