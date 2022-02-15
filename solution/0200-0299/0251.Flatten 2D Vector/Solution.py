class Vector2D:
    def __init__(self, vec: List[List[int]]):
        self.flatten = []
        for item in vec:
            for e in item:
                self.flatten.append(e)
        self.cur = -1

    def next(self) -> int:
        self.cur += 1
        return self.flatten[self.cur]

    def hasNext(self) -> bool:
        return self.cur < len(self.flatten) - 1


# Your Vector2D object will be instantiated and called as such:
# obj = Vector2D(vec)
# param_1 = obj.next()
# param_2 = obj.hasNext()
