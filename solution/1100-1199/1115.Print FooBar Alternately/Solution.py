class FooBar:
    def __init__(self, n):
        self.n = n
        self.fooLock = threading.Lock()
        self.barLock = threading.Lock()
        self.barLock.acquire()

    def foo(self, printFoo: 'Callable[[], None]') -> None:
        for i in range(self.n):
            self.fooLock.acquire()
            printFoo()
            self.barLock.release()

    def bar(self, printBar: 'Callable[[], None]') -> None:
        for i in range(self.n):
            self.barLock.acquire()
            printBar()
            self.fooLock.release()
