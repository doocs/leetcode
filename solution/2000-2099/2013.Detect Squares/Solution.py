class DetectSquares:
    def __init__(self):
        self.mp = defaultdict(Counter)

    def add(self, point: List[int]) -> None:
        x, y = point
        self.mp[x][y] += 1

    def count(self, point: List[int]) -> int:
        x, y = point
        ans = 0
        if x not in self.mp:
            return ans
        xcnt = self.mp[x]

        for x1, counter in self.mp.items():
            if x1 != x:
                d = x1 - x
                ans += xcnt[y + d] * counter[y] * counter[y + d]
                ans += xcnt[y - d] * counter[y] * counter[y - d]
        return ans
