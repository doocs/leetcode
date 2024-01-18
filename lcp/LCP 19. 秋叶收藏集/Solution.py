class Solution:
    def minimumOperations(self, leaves: str) -> int:
        n = len(leaves)
        f = [[inf] * 3 for _ in range(n)]
        f[0][0] = int(leaves[0] == "y")
        for i in range(1, n):
            if leaves[i] == "r":
                f[i][0] = f[i - 1][0]
                f[i][1] = min(f[i - 1][0], f[i - 1][1]) + 1
                f[i][2] = min(f[i - 1][2], f[i - 1][1])
            else:
                f[i][0] = f[i - 1][0] + 1
                f[i][1] = min(f[i - 1][0], f[i - 1][1])
                f[i][2] = min(f[i - 1][2], f[i - 1][1]) + 1
        return f[n - 1][2]
