class Solution:
    def minNumberOfHours(
        self, x: int, y: int, energy: List[int], experience: List[int]
    ) -> int:
        ans = 0
        for dx, dy in zip(energy, experience):
            if x <= dx:
                ans += dx + 1 - x
                x = dx + 1
            if y <= dy:
                ans += dy + 1 - y
                y = dy + 1
            x -= dx
            y += dy
        return ans
