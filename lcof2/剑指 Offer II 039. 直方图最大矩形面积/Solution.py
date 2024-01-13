class Solution:
    def largestRectangleArea(self, heights: List[int]) -> int:
        n = len(heights)
        left = [-1] * n
        right = [n] * n
        stk = []
        for i, x in enumerate(heights):
            while stk and heights[stk[-1]] >= x:
                stk.pop()
            if stk:
                left[i] = stk[-1]
            stk.append(i)
        stk = []
        for i in range(n - 1, -1, -1):
            while stk and heights[stk[-1]] >= heights[i]:
                stk.pop()
            if stk:
                right[i] = stk[-1]
            stk.append(i)
        return max(x * (r - l - 1) for x, l, r in zip(heights, left, right))
