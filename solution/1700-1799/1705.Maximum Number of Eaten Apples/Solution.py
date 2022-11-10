class Solution:
    def eatenApples(self, apples: List[int], days: List[int]) -> int:
        n = len(days)
        i = ans = 0
        q = []
        while i < n or q:
            if i < n and apples[i]:
                heappush(q, (i + days[i] - 1, apples[i]))
            while q and q[0][0] < i:
                heappop(q)
            if q:
                t, v = heappop(q)
                v -= 1
                ans += 1
                if v and t > i:
                    heappush(q, (t, v))
            i += 1
        return ans
