class Solution:
    def minEliminationTime(self, timeReq: List[int], splitTime: int) -> int:
        heapify(timeReq)
        while len(timeReq) > 1:
            heappop(timeReq)
            heappush(timeReq, heappop(timeReq) + splitTime)
        return timeReq[0]
