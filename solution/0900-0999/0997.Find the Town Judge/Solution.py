class Solution:
    def findJudge(self, n: int, trust: List[List[int]]) -> int:
        N = 1001
        c1, c2 = [0] * N, [0] * N
        for a, b in trust:
            c1[a] += 1
            c2[b] += 1
        for i in range(1, N):
            if c1[i] == 0 and c2[i] == n - 1:
                return i
        return -1
