class Solution:
    def bestCoordinate(self, towers: List[List[int]], radius: int) -> List[int]:
        mx = 0
        ans = [0, 0]
        for i in range(51):
            for j in range(51):
                t = 0
                for x, y, q in towers:
                    d = ((x - i) ** 2 + (y - j) ** 2) ** 0.5
                    if d <= radius:
                        t += floor(q / (1 + d))
                if t > mx:
                    mx = t
                    ans = [i, j]
        return ans
