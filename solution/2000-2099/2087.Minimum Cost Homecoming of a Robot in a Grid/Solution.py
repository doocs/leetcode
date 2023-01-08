class Solution:
    def minCost(
        self,
        startPos: List[int],
        homePos: List[int],
        rowCosts: List[int],
        colCosts: List[int],
    ) -> int:
        i, j = startPos
        x, y = homePos
        ans = 0
        if i < x:
            ans += sum(rowCosts[i + 1 : x + 1])
        else:
            ans += sum(rowCosts[x:i])
        if j < y:
            ans += sum(colCosts[j + 1 : y + 1])
        else:
            ans += sum(colCosts[y:j])
        return ans
