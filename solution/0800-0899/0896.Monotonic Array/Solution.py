class Solution:
    def isMonotonic(self, A: List[int]) -> bool:
        increase = decrease = True
        for i in range(1, len(A)):
            if not increase and not decrease:
                return False
            if A[i] < A[i - 1]:
                increase = False
            elif A[i] > A[i - 1]:
                decrease = False
        return increase or decrease
