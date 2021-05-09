class Solution:
    def sortArrayByParity(self, A: List[int]) -> List[int]:
        i, j = 0, len(A) - 1
        while i < j:
            if (A[i] & 1) > (A[j] & 1):
                A[i], A[j] = A[j], A[i]
            if A[i] & 1 == 0:
                i += 1
            if A[j] & 1 == 1:
                j -= 1
        return A
