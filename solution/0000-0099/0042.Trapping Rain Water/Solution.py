class Solution:
    def trap(self, height: List[int]) -> int:
        n = len(height)
        if n < 3:
            return 0

        lmx, rmx = [height[0]] * n, [height[n - 1]] * n
        for i in range(1, n):
            lmx[i] = max(lmx[i - 1], height[i])
            rmx[n - 1 - i] = max(rmx[n - i], height[n - 1 - i])

        res = 0
        for i in range(n):
            res += min(lmx[i], rmx[i]) - height[i]
        return res
