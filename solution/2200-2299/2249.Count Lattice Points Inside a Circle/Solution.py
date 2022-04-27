class Solution:
    def countLatticePoints(self, circles: List[List[int]]) -> int:
        ans = 0
        imx = max(x + r for x, _, r in circles)
        jmx = max(y + r for _, y, r in circles)
        for i in range(imx + 1):
            for j in range(jmx + 1):
                for x, y, r in circles:
                    x, y = x - i, y - j
                    if x * x + y * y <= r * r:
                        ans += 1
                        break
        return ans
