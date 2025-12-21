class Solution:
    def mirrorDistance(self, n: int) -> int:
        return abs(n - int(str(n)[::-1]))
