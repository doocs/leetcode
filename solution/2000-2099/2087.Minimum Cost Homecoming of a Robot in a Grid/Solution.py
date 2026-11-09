class Solution:
    def minCost(
        self,
        startPos: list[int],
        homePos: list[int],
        rowCosts: list[int],
        colCosts: list[int],
    ) -> int:
        x0, y0 = startPos
        x1, y1 = homePos
        dx = sum(rowCosts[x0 + 1 : x1 + 1]) if x0 < x1 else sum(rowCosts[x1:x0])
        dy = sum(colCosts[y0 + 1 : y1 + 1]) if y0 < y1 else sum(colCosts[y1:y0])
        return dx + dy
