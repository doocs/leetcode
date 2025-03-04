class Solution:
    def maxProfit(self, workers: List[int], tasks: List[List[int]]) -> int:
        d = defaultdict(SortedList)
        for skill, profit in tasks:
            d[skill].add(profit)
        ans = 0
        for skill in workers:
            if not d[skill]:
                continue
            ans += d[skill].pop()
        mx = 0
        for ls in d.values():
            if ls:
                mx = max(mx, ls[-1])
        ans += mx
        return ans
