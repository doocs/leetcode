class Solution:
    def minimumCardPickup(self, cards: List[int]) -> int:
        m = {}
        ans = 10**6
        for i, c in enumerate(cards):
            if c in m:
                ans = min(ans, i - m[c] + 1)
            m[c] = i
        return -1 if ans == 10**6 else ans
