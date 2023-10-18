class Solution:
    def isPossible(self, target: List[int]) -> bool:
        if len(target) == 1:
            return target[0] == 1

        summ = sum(target)
        maxHeap = [-num for num in target]
        heapq.heapify(maxHeap)

        while -maxHeap[0] > 1:
            maxi = -heapq.heappop(maxHeap)
            restSum = summ - maxi
            # Only occurs if n == 2.
            if restSum == 1:
                return True
            updated = maxi % restSum
            # Updated == 0 (invalid) or didn't change.
            if updated == 0 or updated == maxi:
                return False
            heapq.heappush(maxHeap, -updated)
            summ = summ - maxi + updated

        return True
