class Solution:
    def isPossible(self, target: List[int]) -> bool:
        s = sum(target)
        pq = [-x for x in target]
        heapify(pq)
        while -pq[0] > 1:
            mx = -heappop(pq)
            t = s - mx
            if t == 0 or mx - t < 1:
                return False
            x = (mx % t) or t
            heappush(pq, -x)
            s = s - mx + x
        return True
