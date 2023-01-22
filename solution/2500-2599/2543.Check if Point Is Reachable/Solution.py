class Solution:
    def isReachable(self, targetX: int, targetY: int) -> bool:
        x = gcd(targetX, targetY)
        return x & (x - 1) == 0
