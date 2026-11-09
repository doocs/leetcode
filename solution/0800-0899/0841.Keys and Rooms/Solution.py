class Solution:
    def canVisitAllRooms(self, rooms: list[list[int]]) -> bool:
        def dfs(i: int):
            if i in vis:
                return
            vis.add(i)
            for j in rooms[i]:
                dfs(j)

        vis = set()
        dfs(0)
        return len(vis) == len(rooms)
