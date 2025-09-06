class Solution:
    def minCost(self, m: int, n: int, waitCost: List[List[int]]) -> int:
        directions = [(1, 0), (0, 1)]  # only down and right
        visited = dict()

        heap = [(1 * 1, 0, 0, 1)]  # (cost, i, j, time)

        while heap:
            cost, i, j, time = heapq.heappop(heap)

            if (i, j, time % 2) in visited and visited[(i, j, time % 2)] <= cost:
                continue
            visited[(i, j, time % 2)] = cost

            if i == m - 1 and j == n - 1:
                return cost

            if time % 2 == 1:  # move step
                for dx, dy in directions:
                    ni, nj = i + dx, j + dy
                    if 0 <= ni < m and 0 <= nj < n:
                        next_cost = cost + (ni + 1) * (nj + 1)
                        heapq.heappush(heap, (next_cost, ni, nj, time + 1))
            else:  # wait step
                next_cost = cost + waitCost[i][j]
                heapq.heappush(heap, (next_cost, i, j, time + 1))

        return -1
