class Solution:
    def sumOddLengthSubarrays(self, arr: List[int]) -> int:
        n = len(arr)
        f = [0] * n
        g = [0] * n
        ans = f[0] = arr[0]
        for i in range(1, n):
            f[i] = g[i - 1] + arr[i] * (i // 2 + 1)
            g[i] = f[i - 1] + arr[i] * ((i + 1) // 2)
            ans += f[i]
        return ans
