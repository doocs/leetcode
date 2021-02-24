class Solution:
    def flipAndInvertImage(self, A: List[List[int]]) -> List[List[int]]:
        m, n = len(A), len(A[0])
        for i in range(m):
            p, q = 0, n - 1
            while p < q:
                t = A[i][p] ^ 1
                A[i][p] = A[i][q] ^ 1
                A[i][q] = t
                p += 1
                q -= 1
            if p == q:
                A[i][p] ^= 1
        return A
