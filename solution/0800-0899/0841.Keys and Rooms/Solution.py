class Solution:
    def canVisitAllRooms(self, rooms: List[List[int]]) -> bool:
        def dfs(u):
            if u == n or u in vis:
                return
            vis.add(u)
            for v in rooms[u]:
                dfs(v)

        n = len(rooms)
        vis = set()
        dfs(0)
        return len(vis) == n
