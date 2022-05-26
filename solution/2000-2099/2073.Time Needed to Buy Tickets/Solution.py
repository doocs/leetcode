class Solution:
    def timeRequiredToBuy(self, tickets: List[int], k: int) -> int:
        ans = 0
        for i, t in enumerate(tickets):
            if i <= k:
                ans += min(tickets[k], t)
            else:
                ans += min(tickets[k] - 1, t)
        return ans
