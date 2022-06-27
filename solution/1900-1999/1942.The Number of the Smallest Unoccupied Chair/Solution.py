class Solution:
    def smallestChair(self, times: List[List[int]], targetFriend: int) -> int:
        n = len(times)
        h = list(range(n))
        heapify(h)
        for i in range(n):
            times[i].append(i)
        times.sort()
        busy = []
        for a, b, i in times:
            while busy and busy[0][0] <= a:
                heappush(h, heappop(busy)[1])
            c = heappop(h)
            if i == targetFriend:
                return c
            heappush(busy, (b, c))
        return -1
