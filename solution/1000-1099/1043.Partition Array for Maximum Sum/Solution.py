class Solution:
    def maxSumAfterPartitioning(self, arr: List[int], k: int) -> int:
        n = len(arr)
        f = [0] * (n + 1)
        for i in range(n):
            mx = 0
            for j in range(i, max(-1, i - k), -1):
                mx = max(mx, arr[j])
                t = mx * (i - j + 1) + f[j]
                f[i + 1] = max(f[i + 1], t)
        return f[n]
