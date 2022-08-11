class Solution:
    def findBuildings(self, heights: List[int]) -> List[int]:
        mx = 0
        ans = []
        for i in range(len(heights) - 1, -1, -1):
            v = heights[i]
            if mx < v:
                ans.append(i)
                mx = v
        return ans[::-1]
