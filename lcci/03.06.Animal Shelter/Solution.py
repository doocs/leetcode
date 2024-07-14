class AnimalShelf:

    def __init__(self):
        self.q = [deque(), deque()]

    def enqueue(self, animal: List[int]) -> None:
        i, j = animal
        self.q[j].append(i)

    def dequeueAny(self) -> List[int]:
        if not self.q[0] or (self.q[1] and self.q[1][0] < self.q[0][0]):
            return self.dequeueDog()
        return self.dequeueCat()

    def dequeueDog(self) -> List[int]:
        return [-1, -1] if not self.q[1] else [self.q[1].popleft(), 1]

    def dequeueCat(self) -> List[int]:
        return [-1, -1] if not self.q[0] else [self.q[0].popleft(), 0]


# Your AnimalShelf object will be instantiated and called as such:
# obj = AnimalShelf()
# obj.enqueue(animal)
# param_2 = obj.dequeueAny()
# param_3 = obj.dequeueDog()
# param_4 = obj.dequeueCat()
