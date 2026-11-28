class Solution:
    def maximumSaleItems(self, items: List[List[int]], budget: int) -> int:
        n = len(items)
        mn = min(p for f, p in items)
        mx = max(f for f, p in items)

        freq = [0] * (mx + 1)
        for f, p in items:
            freq[f] += 1

        cnt = [0] * (mx + 1)
        for f in range(1, mx + 1):
            for j in range(f, mx + 1, f):
                cnt[f] += freq[j]
            cnt[f] -= 1

        deals = [
            (p, cnt[f])
            for f, p in items
            if cnt[f] > 0 and p <= 2 * mn
        ]

        deals.sort()

        ans = 0
        for p, c in deals:
            take = min(c, budget // p)
            ans += take * 2
            budget -= take * p

        return ans + budget // mn
