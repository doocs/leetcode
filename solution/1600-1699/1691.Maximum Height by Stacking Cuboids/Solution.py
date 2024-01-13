class Solution:
    def maxHeight(self, cuboids: List[List[int]]) -> int:
        for c in cuboids:
            c.sort()
        cuboids.sort()
        n = len(cuboids)
        f = [0] * n
        for i in range(n):
            for j in range(i):
                if cuboids[j][1] <= cuboids[i][1] and cuboids[j][2] <= cuboids[i][2]:
                    f[i] = max(f[i], f[j])
            f[i] += cuboids[i][2]
        return max(f)
