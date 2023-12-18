class Solution:
    def countGoodRectangles(self, rectangles: List[List[int]]) -> int:
        ans = mx = 0
        for l, w in rectangles:
            x = min(l, w)
            if mx < x:
                ans = 1
                mx = x
            elif mx == x:
                ans += 1
        return ans
