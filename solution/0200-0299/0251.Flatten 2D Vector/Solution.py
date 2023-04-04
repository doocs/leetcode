class Vector2D:
    def __init__(self, vec: List[List[int]]):
        self.i = 0
        self.j = 0
        self.vec = vec

    def next(self) -> int:
        self.forward()
        ans = self.vec[self.i][self.j]
        self.j += 1
        return ans

    def hasNext(self) -> bool:
        self.forward()
        return self.i < len(self.vec)

    def forward(self):
        while self.i < len(self.vec) and self.j >= len(self.vec[self.i]):
            self.i += 1
            self.j = 0


# Your Vector2D object will be instantiated and called as such:
# obj = Vector2D(vec)
# param_1 = obj.next()
# param_2 = obj.hasNext()
