import heapq

class Solution:
    def getLeastNumbers(self, arr: List[int], k: int) -> List[int]:
        if k == 0:
            return []
        heap = []
        for e in arr:
            heapq.heappush(heap, e)
        return heapq.nsmallest(k, heap)