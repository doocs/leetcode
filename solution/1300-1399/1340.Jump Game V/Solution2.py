class Solution:
    def maxJumps(self, arr: List[int], d: int) -> int:
        n = len(arr)
        f = [1] * n
        for x, i in sorted(zip(arr, range(n))):
            for j in range(i - 1, -1, -1):
                if i - j > d or arr[j] >= x:
                    break
                f[i] = max(f[i], 1 + f[j])
            for j in range(i + 1, n):
                if j - i > d or arr[j] >= x:
                    break
                f[i] = max(f[i], 1 + f[j])
        return max(f)
