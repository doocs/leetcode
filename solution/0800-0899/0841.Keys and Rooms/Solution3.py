class Solution:
    def canVisitAllRooms(self, rooms: List[List[int]]) -> bool:
        n = len(rooms)
        vis = [False] * n
        stk = [0]
        while stk:
            i = stk.pop()
            if vis[i]:
                continue
            vis[i] = True
            for j in rooms[i]:
                stk.append(j)
        return all(vis)
