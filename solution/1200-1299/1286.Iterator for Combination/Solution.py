class CombinationIterator:
    def __init__(self, characters: str, combinationLength: int):
        self.curr = (1 << len(characters)) - 1
        self.size = combinationLength
        self.cs = characters[::-1]

    def next(self) -> str:
        while self.curr >= 0 and self.curr.bit_count() != self.size:
            self.curr -= 1
        ans = []
        for i in range(len(self.cs)):
            if (self.curr >> i) & 1:
                ans.append(self.cs[i])
        self.curr -= 1
        return ''.join(ans[::-1])

    def hasNext(self) -> bool:
        while self.curr >= 0 and self.curr.bit_count() != self.size:
            self.curr -= 1
        return self.curr >= 0


# Your CombinationIterator object will be instantiated and called as such:
# obj = CombinationIterator(characters, combinationLength)
# param_1 = obj.next()
# param_2 = obj.hasNext()
