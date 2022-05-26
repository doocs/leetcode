class Solution:
    def findOrder(self, numCourses: int, prerequisites: List[List[int]]) -> List[int]:
        edges = defaultdict(list)
        indegree = [0] * numCourses
        for a, b in prerequisites:
            edges[b].append(a)
            indegree[a] += 1
        q = deque()
        for i in range(numCourses):
            if indegree[i] == 0:
                q.append(i)
        ans = []
        while q:
            b = q.popleft()
            ans.append(b)
            for a in edges[b]:
                indegree[a] -= 1
                if indegree[a] == 0:
                    q.append(a)
        return ans if len(ans) == numCourses else []
