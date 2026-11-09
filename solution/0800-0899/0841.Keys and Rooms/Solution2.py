class Solution:
    def canVisitAllRooms(self, rooms: list[list[int]]) -> bool:
        vis = set()
        q = deque([0])
        while q:
            i = q.popleft()
            if i in vis:
                continue
            vis.add(i)
            q.extend(j for j in rooms[i])
        return len(vis) == len(rooms)
