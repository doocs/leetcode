class Solution:
    def largestRectangleArea(self, heights: List[int]) -> int:
        res, n = 0, len(heights)
        stk = []
        left = [-1] * n
        right = [n] * n
        for i, h in enumerate(heights):
            while stk and heights[stk[-1]] >= h:
                right[stk[-1]] = i
                stk.pop()
            if stk:
                left[i] = stk[-1]
            stk.append(i)
        for i, h in enumerate(heights):
            res = max(res, h * (right[i] - left[i] - 1))
        return res
