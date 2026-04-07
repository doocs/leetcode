class Robot:

    def __init__(self, width: int, height: int):
        self.mx = width - 1
        self.my = height - 1
        self.p = 2 * self.mx + 2 * self.my
        self.cur = 0
        self.moved = False

    def step(self, num: int) -> None:
        self.moved = True
        self.cur = (self.cur + num) % self.p

    def getPos(self) -> List[int]:
        d = self.cur
        mx, my = self.mx, self.my
        if 0 <= d <= mx:
            return [d, 0]
        if mx < d <= mx + my:
            return [mx, d - mx]
        if mx + my < d <= 2 * mx + my:
            return [mx - (d - (mx + my)), my]
        return [0, my - (d - (2 * mx + my))]

    def getDir(self) -> str:
        d = self.cur
        mx, my = self.mx, self.my
        if not self.moved:
            return "East"
        if 1 <= d <= mx:
            return "East"
        elif mx < d <= mx + my:
            return "North"
        elif mx + my < d <= 2 * mx + my:
            return "West"
        return "South"


# Your Robot object will be instantiated and called as such:
# obj = Robot(width, height)
# obj.step(num)
# param_2 = obj.getPos()
# param_3 = obj.getDir()
