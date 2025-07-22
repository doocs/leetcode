class Solution:
    def maxSum(self, grid: List[List[int]], limits: List[int], k: int) -> int:
        pq = []
        for nums, limit in zip(grid, limits):
            nums.sort()
            for _ in range(limit):
                heappush(pq, nums.pop())
                if len(pq) > k:
                    heappop(pq)
        return sum(pq)
