class Node:
    def __init__(self, val: int, level: int):
        self.val = val
        self.next = [None for _ in range(level)]


class Skiplist:
    max_level = 16
    p = 0.5

    def __init__(self):
        self.head = Node(-1, self.max_level)
        self.level = 1

    def search(self, target: int) -> bool:
        p = self.head
        for i in range(self.level - 1, -1, -1):
            p = self.find_closest(p, i, target)
            if p.next[i] != None and p.next[i].val == target:
                return True
        return False

    def add(self, num: int) -> None:
        level = self.random_level()
        self.level = max(self.level, level)
        node = Node(num, level)
        p = self.head
        for i in range(self.level - 1, -1, -1):
            p = self.find_closest(p, i, num)
            if i < level:
                node.next[i] = p.next[i]
                p.next[i] = node

    def erase(self, num: int) -> bool:
        ok = False
        p = self.head
        for i in range(self.level - 1, -1, -1):
            p = self.find_closest(p, i, num)
            if p.next[i] != None and p.next[i].val == num:
                p.next[i] = p.next[i].next[i]
                ok = True
        while self.level > 1 and self.head.next[self.level - 1] == None:
            self.level -= 1
        return ok

    def find_closest(self, p: Node, level: int, target: int) -> Node:
        while p.next[level] != None and p.next[level].val < target:
            p = p.next[level]
        return p

    def random_level(self) -> int:
        level = 1
        while level < self.max_level and random.random() < self.p:
            level += 1
        return level


# Your Skiplist object will be instantiated and called as such:
# obj = Skiplist()
# param_1 = obj.search(target)
# obj.add(num)
# param_3 = obj.erase(num)
