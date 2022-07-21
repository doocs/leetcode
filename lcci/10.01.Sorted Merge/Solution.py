class Solution:
    def merge(self, A: List[int], m: int, B: List[int], n: int) -> None:
        """
        Do not return anything, modify A in-place instead.
        """
        i, j = m - 1, n - 1
        for k in range(len(A) - 1, -1, -1):
            if j < 0 or (i >= 0 and A[i] >= B[j]):
                A[k] = A[i]
                i -= 1
            else:
                A[k] = B[j]
                j -= 1
