class Solution:
    def checkIfPrerequisite(
        self, numCourses: int, prerequisites: List[List[int]], queries: List[List[int]]
    ) -> List[bool]:
        @cache
        def dfs(a, b):
            if b in g[a] or a == b:
                return True
            for c in g[a]:
                if dfs(c, b):
                    return True
            return False

        g = defaultdict(set)
        for a, b in prerequisites:
            g[a].add(b)
        return [dfs(a, b) for a, b in queries]
