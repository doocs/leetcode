from sortedcontainers import SortedList


class Solution:
    def busiestServers(self, k: int, arrival: List[int], load: List[int]) -> List[int]:
        free = SortedList(range(k))
        busy = []
        cnt = [0] * k
        for i, (start, t) in enumerate(zip(arrival, load)):
            while busy and busy[0][0] <= start:
                free.add(busy[0][1])
                heappop(busy)
            if not free:
                continue
            j = free.bisect_left(i % k)
            if j == len(free):
                j = 0
            server = free[j]
            cnt[server] += 1
            heappush(busy, (start + t, server))
            free.remove(server)

        mx = max(cnt)
        return [i for i, v in enumerate(cnt) if v == mx]
