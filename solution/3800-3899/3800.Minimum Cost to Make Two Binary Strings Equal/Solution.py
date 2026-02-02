class Solution:
    def minimumCost(
        self, s: str, t: str, flipCost: int, swapCost: int, crossCost: int
    ) -> int:
        diff = [0] * 2
        for c1, c2 in zip(s, t):
            if c1 != c2:
                diff[int(c1)] += 1
        ans = (diff[0] + diff[1]) * flipCost
        mx = max(diff)
        mn = min(diff)
        ans = min(ans, mn * swapCost + (mx - mn) * flipCost)
        avg = (mx + mn) // 2
        ans = min(
            ans,
            (avg - mn) * crossCost + avg * swapCost + (mx + mn - avg * 2) * flipCost,
        )
        return ans
