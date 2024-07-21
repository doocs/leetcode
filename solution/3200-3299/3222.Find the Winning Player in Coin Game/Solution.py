class Solution:
    def losingPlayer(self, x: int, y: int) -> str:
        k = min(x // 2, y // 8)
        x -= k * 2
        y -= k * 8
        return "Alice" if x and y >= 4 else "Bob"
