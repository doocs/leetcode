class Solution:
    def minStoneSum(self, piles: list[int], k: int) -> int:
        pq = [-x for x in piles]
        heapify(pq)
        for _ in range(k):
            heapreplace(pq, pq[0] // 2)
        return -sum(pq)
