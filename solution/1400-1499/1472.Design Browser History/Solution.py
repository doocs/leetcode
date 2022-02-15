class BrowserHistory:
    def __init__(self, homepage: str):
        self.urls = []
        self.cur = -1
        self.tail = -1
        self.visit(homepage)

    def visit(self, url: str) -> None:
        self.cur += 1
        if self.cur < len(self.urls):
            self.urls[self.cur] = url
        else:
            self.urls.append(url)
        self.tail = self.cur

    def back(self, steps: int) -> str:
        self.cur = max(0, self.cur - steps)
        return self.urls[self.cur]

    def forward(self, steps: int) -> str:
        self.cur = min(self.tail, self.cur + steps)
        return self.urls[self.cur]


# Your BrowserHistory object will be instantiated and called as such:
# obj = BrowserHistory(homepage)
# obj.visit(url)
# param_2 = obj.back(steps)
# param_3 = obj.forward(steps)
