class Solution:
    def repeatedNTimes(self, A):
        """
        :type A: List[int]
        :rtype: int
        """
        if A[0] == A[1] or A[0] == A[2] or A[0] == A[3]:
            return A[0]
        elif A[1] == A[2] or A[1] == A[3]:
            return A[1]
        elif A[2] == A[3]:
            return A[2]
        i = 4
        while i < len(A):
            if A[i] == A[i + 1]:
                return A[i]
            i += 2
