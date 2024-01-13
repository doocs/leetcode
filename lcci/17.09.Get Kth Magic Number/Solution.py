class Solution:
    def getKthMagicNumber(self, k: int) -> int:
        h = [1]
        vis = {1}
        for _ in range(k - 1):
            cur = heappop(h)
            for f in (3, 5, 7):
                if (nxt := cur * f) not in vis:
                    vis.add(nxt)
                    heappush(h, nxt)
        return h[0]
