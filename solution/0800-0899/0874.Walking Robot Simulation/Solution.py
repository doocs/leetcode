class Solution:
    def robotSim(self, commands: List[int], obstacles: List[List[int]]) -> int:
        dirs = [[-1, 0], [0, 1], [1, 0], [0, -1]]
        s = {(x, y) for x, y in obstacles}
        ans, p = 0, 1
        x = y = 0
        for v in commands:
            if v == -2:
                p = (p + 3) % 4
            elif v == -1:
                p = (p + 1) % 4
            else:
                for _ in range(v):
                    nx, ny = x + dirs[p][0], y + dirs[p][1]
                    if (nx, ny) in s:
                        break
                    x, y = nx, ny
                    ans = max(ans, x * x + y * y)
        return ans
