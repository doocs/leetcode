class Solution:
    def minArrivalsToDiscard(self, arrivals: List[int], w: int, m: int) -> int:
        cnt = Counter()
        n = len(arrivals)
        marked = [0] * n
        ans = 0
        for i, x in enumerate(arrivals):
            if i >= w:
                cnt[arrivals[i - w]] -= marked[i - w]
            if cnt[x] >= m:
                ans += 1
            else:
                marked[i] = 1
                cnt[x] += 1
        return ans
