class Trie:
    def __init__(self):
        self.children: List[Trie | None] = [None] * 26
        self.val: int = 0

    def insert(self, w: str, x: int):
        node = self
        for c in w:
            idx = ord(c) - ord('a')
            if node.children[idx] is None:
                node.children[idx] = Trie()
            node = node.children[idx]
            node.val += x

    def search(self, w: str) -> int:
        node = self
        for c in w:
            idx = ord(c) - ord('a')
            if node.children[idx] is None:
                return 0
            node = node.children[idx]
        return node.val


class MapSum:
    def __init__(self):
        self.d = defaultdict(int)
        self.tree = Trie()

    def insert(self, key: str, val: int) -> None:
        x = val - self.d[key]
        self.d[key] = val
        self.tree.insert(key, x)

    def sum(self, prefix: str) -> int:
        return self.tree.search(prefix)


# Your MapSum object will be instantiated and called as such:
# obj = MapSum()
# obj.insert(key,val)
# param_2 = obj.sum(prefix)
