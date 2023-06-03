from threading import Semaphore


class BoundedBlockingQueue(object):
    def __init__(self, capacity: int):
        self.s1 = Semaphore(capacity)
        self.s2 = Semaphore(0)
        self.q = deque()

    def enqueue(self, element: int) -> None:
        self.s1.acquire()
        self.q.append(element)
        self.s2.release()

    def dequeue(self) -> int:
        self.s2.acquire()
        ans = self.q.popleft()
        self.s1.release()
        return ans

    def size(self) -> int:
        return len(self.q)
