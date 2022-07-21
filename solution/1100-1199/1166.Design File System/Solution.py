class Trie:
    def __init__(self):
        self.children = {}
        self.v = 0

    def insert(self, w, v):
        node = self
        ps = w.split('/')
        for p in ps[1:-1]:
            if p not in node.children:
                return False
            node = node.children[p]
        if ps[-1] in node.children:
            return False
        node.children[ps[-1]] = Trie()
        node = node.children[ps[-1]]
        node.v = v
        return True

    def search(self, w):
        node = self
        for p in w.split('/')[1:]:
            if p not in node.children:
                return -1
            node = node.children[p]
        return node.v or -1


class FileSystem:
    def __init__(self):
        self.trie = Trie()

    def createPath(self, path: str, value: int) -> bool:
        return self.trie.insert(path, value)

    def get(self, path: str) -> int:
        return self.trie.search(path)


# Your FileSystem object will be instantiated and called as such:
# obj = FileSystem()
# param_1 = obj.createPath(path,value)
# param_2 = obj.get(path)
