class Solution:
    def merge(self, A: list[int], m: int, B: list[int], n: int) -> None:
        i, j = m - 1, n - 1
        for k in reversed(range(m + n)):
            if j < 0 or i >= 0 and A[i] > B[j]:
                A[k] = A[i]
                i -= 1
            else:
                A[k] = B[j]
                j -= 1
