class Solution:
    def smallestChair(self, times: List[List[int]], targetFriend: int) -> int:
        n = len(times)
        for i in range(n):
            times[i].append(i)
        times.sort()
        idle = list(range(n))
        heapify(idle)
        busy = []
        for arrival, leaving, i in times:
            while busy and busy[0][0] <= arrival:
                heappush(idle, heappop(busy)[1])
            j = heappop(idle)
            if i == targetFriend:
                return j
            heappush(busy, (leaving, j))
