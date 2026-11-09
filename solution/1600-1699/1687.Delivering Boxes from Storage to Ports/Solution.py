class Solution:
    def boxDelivering(
        self, boxes: list[list[int]], portsCount: int, maxBoxes: int, maxWeight: int
    ) -> int:
        n = len(boxes)
        ws = list(accumulate((box[1] for box in boxes), initial=0))
        c = [int(a != b) for a, b in pairwise(box[0] for box in boxes)]
        cs = list(accumulate(c, initial=0))
        f = [inf] * (n + 1)
        f[0] = 0
        for i in range(1, n + 1):
            for j in range(max(0, i - maxBoxes), i):
                if ws[i] - ws[j] <= maxWeight:
                    f[i] = min(f[i], f[j] + cs[i - 1] - cs[j] + 2)
        return f[n]
