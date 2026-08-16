class Solution:
    def elevatorRequests(self, n: int, start: int, requests: list[list[int]]) -> int:
        m = len(requests)
        f = [[0] * m for _ in range(1 << m)]
        for i in range(1 << m):
            for j in range(m):
                if i >> j & 1:
                    f[i][j] = inf
                    i0 = i ^ (1 << j)
                    if i0 == 0:
                        d = abs(start - requests[j][1])
                        f[i][j] = min(f[i][j], max(d, requests[j][0]))
                    else:
                        for j0 in range(m):
                            if j0 != j and (i >> j0 & 1):
                                d = abs(requests[j0][1] - requests[j][1])
                                f[i][j] = min(
                                    f[i][j], max(f[i0][j0] + d, requests[j][0])
                                )
        return min(f[(1 << m) - 1][j] for j in range(m))
