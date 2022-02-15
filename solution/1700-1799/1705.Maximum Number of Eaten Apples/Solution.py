class Solution:
    def eatenApples(self, apples: List[int], days: List[int]) -> int:
        q = []
        n = len(apples)
        i = ans = 0
        while i < n or q:
            if i < n and apples[i] > 0:
                heappush(q, [i + days[i] - 1, apples[i]])
            while q and q[0][0] < i:
                heappop(q)
            if q:
                end, num = heappop(q)
                num -= 1
                ans += 1
                if num > 0 and end > i:
                    heappush(q, [end, num])
            i += 1
        return ans
