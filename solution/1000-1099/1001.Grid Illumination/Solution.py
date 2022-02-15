class Solution:
    def gridIllumination(
        self, n: int, lamps: List[List[int]], queries: List[List[int]]
    ) -> List[int]:
        points = set()
        rcnt, ccnt, dgcnt, udgcnt = Counter(), Counter(), Counter(), Counter()
        for r, c in lamps:
            if (r, c) not in points:
                points.add((r, c))
                rcnt[r] += 1
                ccnt[c] += 1
                dgcnt[r - c] += 1
                udgcnt[r + c] += 1
        ans = [0] * len(queries)
        for i, q in enumerate(queries):
            r, c = q
            if rcnt[r] or ccnt[c] or dgcnt[r - c] or udgcnt[r + c]:
                ans[i] = 1
                for a, b in [
                    (0, 1),
                    (1, 0),
                    (0, -1),
                    (-1, 0),
                    (0, 0),
                    (1, 1),
                    (-1, 1),
                    (1, -1),
                    (-1, -1),
                ]:
                    x, y = r + a, c + b
                    if (x, y) in points:
                        points.remove((x, y))
                        rcnt[x] -= 1
                        ccnt[y] -= 1
                        dgcnt[x - y] -= 1
                        udgcnt[x + y] -= 1
        return ans
