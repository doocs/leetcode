class Solution:
    def minTime(self, n: int, edges: List[List[int]]) -> int:
        minReachTime = [inf] * n
        minReachTime[0] = 0

        nodeEdges = [[] for _ in range(n)]
        for edge in edges:
            nodeEdges[edge[0]].append(edge)

        reachTimeHeap = [(0, 0)]
        while reachTimeHeap:
            curTime, node = heappop(reachTimeHeap)
            if node == n - 1:
                return curTime

            for edge in nodeEdges[node]:
                if curTime <= edge[3]:
                    destTime = max(curTime, edge[2]) + 1
                    if minReachTime[edge[1]] > destTime:
                        minReachTime[edge[1]] = destTime
                        heappush(reachTimeHeap, (destTime, edge[1]))

        return -1
