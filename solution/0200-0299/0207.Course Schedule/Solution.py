class Solution:
    def canFinish(self, numCourses: int, prerequisites: List[List[int]]) -> bool:
        edges = defaultdict(list)
        indegree = [0] * numCourses
        for a, b in prerequisites:
            edges[b].append(a)
            indegree[a] += 1
        q = deque()
        for i in range(numCourses):
            if indegree[i] == 0:
                q.append(i)
        n = 0
        while q:
            b = q.popleft()
            n += 1
            for a in edges[b]:
                indegree[a] -= 1
                if indegree[a] == 0:
                    q.append(a)
        return n == numCourses
