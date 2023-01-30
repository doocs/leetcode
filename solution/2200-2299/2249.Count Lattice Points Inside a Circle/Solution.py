class Solution:
    def countLatticePoints(self, circles: List[List[int]]) -> int:
        ans = 0
        mx = max(x + r for x, _, r in circles)
        my = max(y + r for _, y, r in circles)
        for i in range(mx + 1):
            for j in range(my + 1):
                for x, y, r in circles:
                    dx, dy = i - x, j - y
                    if dx * dx + dy * dy <= r * r:
                        ans += 1
                        break
        return ans
