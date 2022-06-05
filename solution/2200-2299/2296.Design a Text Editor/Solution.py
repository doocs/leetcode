class TextEditor:
    def __init__(self):
        self.idx = 0
        self.s = []

    def addText(self, text: str) -> None:
        t = list(text)
        self.s[self.idx : self.idx] = t
        self.idx += len(t)

    def deleteText(self, k: int) -> int:
        k = min(self.idx, k)
        self.s[self.idx - k : self.idx] = []
        self.idx -= k
        return k

    def cursorLeft(self, k: int) -> str:
        self.idx = max(0, self.idx - k)
        return ''.join(self.s[max(0, self.idx - 10) : self.idx])

    def cursorRight(self, k: int) -> str:
        self.idx = min(len(self.s), self.idx + k)
        return ''.join(self.s[max(0, self.idx - 10) : self.idx])


# Your TextEditor object will be instantiated and called as such:
# obj = TextEditor()
# obj.addText(text)
# param_2 = obj.deleteText(k)
# param_3 = obj.cursorLeft(k)
# param_4 = obj.cursorRight(k)
