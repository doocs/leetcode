class AnimalShelf:
    def __init__(self):
        self.cats = []
        self.dogs = []

    def enqueue(self, animal: List[int]) -> None:
        if animal[1] == 0:
            self.cats.insert(0, animal[0])
        else:
            self.dogs.insert(0, animal[0])

    def dequeueAny(self) -> List[int]:
        if len(self.dogs) == 0:
            return self.dequeueCat()
        if len(self.cats) == 0:
            return self.dequeueDog()
        return self.dequeueDog() if self.dogs[-1] < self.cats[-1] else self.dequeueCat()

    def dequeueDog(self) -> List[int]:
        return [-1, -1] if len(self.dogs) == 0 else [self.dogs.pop(), 1]

    def dequeueCat(self) -> List[int]:
        return [-1, -1] if len(self.cats) == 0 else [self.cats.pop(), 0]


# Your AnimalShelf object will be instantiated and called as such:
# obj = AnimalShelf()
# obj.enqueue(animal)
# param_2 = obj.dequeueAny()
# param_3 = obj.dequeueDog()
# param_4 = obj.dequeueCat()
