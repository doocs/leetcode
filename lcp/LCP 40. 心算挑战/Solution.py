class Solution:
    def maxmiumScore(self, cards: List[int], cnt: int) -> int:
        cards.sort()
        ans = sum(cards[-cnt:])
        if ans % 2 == 0:
            return ans
        n = len(cards)
        mx1 = mx2 = -inf
        for x in cards[: n - cnt]:
            if x & 1:
                mx1 = x
            else:
                mx2 = x
        mi1 = mi2 = inf
        for x in cards[-cnt:][::-1]:
            if x & 1:
                mi2 = x
            else:
                mi1 = x
        ans = max(ans - mi1 + mx1, ans - mi2 + mx2, -1)
        return 0 if ans < 0 else ans
