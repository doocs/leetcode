class Solution:
    def mostBooked(self, n: int, meetings: List[List[int]]) -> int:
        meetings.sort()
        busy = []
        idle = list(range(n))
        heapify(idle)
        cnt = [0] * n
        for s, e in meetings:
            while busy and busy[0][0] <= s:
                heappush(idle, heappop(busy)[1])
            if idle:
                i = heappop(idle)
                cnt[i] += 1
                heappush(busy, (e, i))
            else:
                a, i = heappop(busy)
                cnt[i] += 1
                heappush(busy, (a + e - s, i))
        ans = 0
        for i, v in enumerate(cnt):
            if cnt[ans] < v:
                ans = i
        return ans
