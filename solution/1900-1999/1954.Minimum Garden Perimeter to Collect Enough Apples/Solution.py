class Solution:
    def minimumPerimeter(self, neededApples: int) -> int:
        x = 1
        while 2 * x * (x + 1) * (2 * x + 1) < neededApples:
            x += 1
        return x * 8
