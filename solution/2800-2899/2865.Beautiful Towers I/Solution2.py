class Solution:
    def maximumSumOfHeights(self, maxHeights: List[int]) -> int:
        n = len(maxHeights)
        stk = []
        left = [-1] * n
        for i, x in enumerate(maxHeights):
            while stk and maxHeights[stk[-1]] > x:
                stk.pop()
            if stk:
                left[i] = stk[-1]
            stk.append(i)
        stk = []
        right = [n] * n
        for i in range(n - 1, -1, -1):
            x = maxHeights[i]
            while stk and maxHeights[stk[-1]] >= x:
                stk.pop()
            if stk:
                right[i] = stk[-1]
            stk.append(i)
        f = [0] * n
        for i, x in enumerate(maxHeights):
            if i and x >= maxHeights[i - 1]:
                f[i] = f[i - 1] + x
            else:
                j = left[i]
                f[i] = x * (i - j) + (f[j] if j != -1 else 0)
        g = [0] * n
        for i in range(n - 1, -1, -1):
            if i < n - 1 and maxHeights[i] >= maxHeights[i + 1]:
                g[i] = g[i + 1] + maxHeights[i]
            else:
                j = right[i]
                g[i] = maxHeights[i] * (j - i) + (g[j] if j != n else 0)
        return max(a + b - c for a, b, c in zip(f, g, maxHeights))
