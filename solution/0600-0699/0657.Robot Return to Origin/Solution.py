class Solution:
    def judgeCircle(self, moves: str) -> bool:
        x = y = 0
        for c in moves:
            match c:
                case "U":
                    y += 1
                case "D":
                    y -= 1
                case "L":
                    x -= 1
                case "R":
                    x += 1
        return x == 0 and y == 0
