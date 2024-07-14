class Solution:
    def totalCost(self, costs: List[int], k: int, candidates: int) -> int:
        n = len(costs)
        if candidates * 2 >= n:
            return sum(sorted(costs)[:k])
        pq = []
        for i, c in enumerate(costs[:candidates]):
            heappush(pq, (c, i))
        for i in range(n - candidates, n):
            heappush(pq, (costs[i], i))
        heapify(pq)
        l, r = candidates, n - candidates - 1
        ans = 0
        for _ in range(k):
            c, i = heappop(pq)
            ans += c
            if l > r:
                continue
            if i < l:
                heappush(pq, (costs[l], l))
                l += 1
            else:
                heappush(pq, (costs[r], r))
                r -= 1
        return ans
