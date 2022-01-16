class Solution:
    def canVisitAllRooms(self, rooms: List[List[int]]) -> bool:
        def dfs(u):
            if u in vis:
                return
            vis.add(u)
            for v in rooms[u]:
                dfs(v)

        vis = set()
        dfs(0)
        return len(vis) == len(rooms)
