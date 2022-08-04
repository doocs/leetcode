class Solution:
    def maxmiumScore(self, cards: List[int], cnt: int) -> int:
        cards.sort(reverse=True)
        t = cards[:cnt]
        ans = sum(t)
        if ans % 2 == 0:
            return ans
        a = min([v for v in t if v & 1], default=inf)
        b = min([v for v in t if v % 2 == 0], default=inf)
        c = max([v for v in cards[cnt:] if v % 2 == 0], default=-inf)
        d = max([v for v in cards[cnt:] if v & 1], default=-inf)
        return max(ans - a + c, ans - b + d, 0)
