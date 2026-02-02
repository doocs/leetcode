class Solution:
    def bestTower(
        self, towers: List[List[int]], center: List[int], radius: int
    ) -> List[int]:
        cx, cy = center
        idx = -1
        for i, (x, y, q) in enumerate(towers):
            dist = abs(x - cx) + abs(y - cy)
            if dist > radius:
                continue
            if (
                idx == -1
                or towers[idx][2] < q
                or (towers[idx][2] == q and towers[i][:2] < towers[idx][:2])
            ):
                idx = i
        return [-1, -1] if idx == -1 else towers[idx][:2]
