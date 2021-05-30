class Solution:
    def assignTasks(self, servers: List[int], tasks: List[int]) -> List[int]:
        idle, busy = [], []
        for i, weight in enumerate(servers):
            heapq.heappush(idle, (weight, i))
        res = []
        for start, cost in enumerate(tasks):
            while busy and busy[0][0] <= start:
                _, s, i = heapq.heappop(busy)
                heapq.heappush(idle, (s, i))
            if idle:
                s, i = heapq.heappop(idle)
                heapq.heappush(busy, (start + cost, s, i))
            else:
                t, s, i = heapq.heappop(busy)
                heapq.heappush(busy, (t + cost, s, i))
            res.append(i)
        return res
