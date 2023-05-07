from threading import Semaphore


class FooBar:
    def __init__(self, n):
        self.n = n
        self.f = Semaphore(1)
        self.b = Semaphore(0)

    def foo(self, printFoo: "Callable[[], None]") -> None:
        for _ in range(self.n):
            self.f.acquire()
            # printFoo() outputs "foo". Do not change or remove this line.
            printFoo()
            self.b.release()

    def bar(self, printBar: "Callable[[], None]") -> None:
        for _ in range(self.n):
            self.b.acquire()
            # printBar() outputs "bar". Do not change or remove this line.
            printBar()
            self.f.release()
