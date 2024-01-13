class Solution:
    def numberOfCleanRooms(self, room: List[List[int]]) -> int:
        dirs = (0, 1, 0, -1, 0)
        i = j = k = 0
        ans = 0
        vis = set()
        while (i, j, k) not in vis:
            vis.add((i, j, k))
            ans += room[i][j] == 0
            room[i][j] = -1
            x, y = i + dirs[k], j + dirs[k + 1]
            if 0 <= x < len(room) and 0 <= y < len(room[0]) and room[x][y] != 1:
                i, j = x, y
            else:
                k = (k + 1) % 4
        return ans
