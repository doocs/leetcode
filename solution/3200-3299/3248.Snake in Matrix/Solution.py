class Solution:
    def finalPositionOfSnake(self, n: int, commands: List[str]) -> int:
        x = y = 0
        for c in commands:
            match c[0]:
                case "U":
                    x -= 1
                case "D":
                    x += 1
                case "L":
                    y -= 1
                case "R":
                    y += 1
        return x * n + y
