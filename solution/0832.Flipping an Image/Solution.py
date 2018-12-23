class Solution:
    def flipAndInvertImage(self, A):
        """
        :type A: List[List[int]]
        :rtype: List[List[int]]
        """

        Len = len(A[0])

        for row in A:
            for i in range( (Len + 1) // 2 ):
                row[i], row[Len - i - 1] = int(not row[Len - i - 1]), int(not row[i])

        return A
