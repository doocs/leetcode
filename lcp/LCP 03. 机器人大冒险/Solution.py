class Solution:
    def robot(self, command: str, obstacles: List[List[int]], x: int, y: int) -> bool:
        vis = {(0, 0)}
        i = j = 0
        for c in command:
            match c:
                case "U":
                    j += 1
                case "R":
                    i += 1
            vis.add((i, j))
        k = min(x // i, y // j)
        if (x - k * i, y - k * j) not in vis:
            return False
        for a, b in obstacles:
            if a > x or b > y:
                continue
            k = min(a // i, b // j)
            if (a - k * i, b - k * j) in vis:
                return False
        return True
