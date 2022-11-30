class Solution:
    def maximumSum(self, arr: List[int]) -> int:
        n = len(arr)
        left = [0] * n
        right = [0] * n
        t = 0
        for i, v in enumerate(arr):
            t = max(t, 0) + v
            left[i] = t
        t = 0
        for i in range(n - 1, -1, -1):
            t = max(t, 0) + arr[i]
            right[i] = t
        ans = max(left)
        for i in range(1, n - 1):
            ans = max(ans, left[i - 1] + right[i + 1])
        return ans
