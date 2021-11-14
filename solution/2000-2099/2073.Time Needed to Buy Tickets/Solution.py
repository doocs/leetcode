class Solution:
    def timeRequiredToBuy(self, tickets: List[int], k: int) -> int:
        i = ans = 0
        while True:
            if i == k and tickets[i] == 1:
                return ans + 1
            if tickets[i] > 0:
                tickets[i] -= 1
                ans += 1
            i = (i + 1) % len(tickets)
