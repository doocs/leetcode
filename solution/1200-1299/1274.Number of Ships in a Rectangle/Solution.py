# """
# This is Sea's API interface.
# You should not implement it, or speculate about its implementation
# """
# class Sea:
#    def hasShips(self, topRight: 'Point', bottomLeft: 'Point') -> bool:
#
# class Point:
# 	def __init__(self, x: int, y: int):
# 		self.x = x
# 		self.y = y


class Solution:
    def countShips(self, sea: "Sea", topRight: "Point", bottomLeft: "Point") -> int:
        def dfs(topRight, bottomLeft):
            x1, y1 = bottomLeft.x, bottomLeft.y
            x2, y2 = topRight.x, topRight.y
            if x1 > x2 or y1 > y2:
                return 0
            if not sea.hasShips(topRight, bottomLeft):
                return 0
            if x1 == x2 and y1 == y2:
                return 1
            midx = (x1 + x2) >> 1
            midy = (y1 + y2) >> 1
            a = dfs(topRight, Point(midx + 1, midy + 1))
            b = dfs(Point(midx, y2), Point(x1, midy + 1))
            c = dfs(Point(midx, midy), bottomLeft)
            d = dfs(Point(x2, midy), Point(midx + 1, y1))
            return a + b + c + d

        return dfs(topRight, bottomLeft)
