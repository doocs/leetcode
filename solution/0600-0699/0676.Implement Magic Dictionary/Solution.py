class Trie:
    __slots__ = ["children", "is_end"]

    def __init__(self):
        self.children = {}
        self.is_end = False

    def insert(self, w: str) -> None:
        node = self
        for c in w:
            if c not in node.children:
                node.children[c] = Trie()
            node = node.children[c]
        node.is_end = True

    def search(self, w: str) -> bool:
        def dfs(i: int, node: Trie, diff: int) -> bool:
            if i == len(w):
                return diff == 1 and node.is_end
            if w[i] in node.children and dfs(i + 1, node.children[w[i]], diff):
                return True
            return diff == 0 and any(
                dfs(i + 1, node.children[c], 1) for c in node.children if c != w[i]
            )

        return dfs(0, self, 0)


class MagicDictionary:
    def __init__(self):
        self.trie = Trie()

    def buildDict(self, dictionary: List[str]) -> None:
        for w in dictionary:
            self.trie.insert(w)

    def search(self, searchWord: str) -> bool:
        return self.trie.search(searchWord)


# Your MagicDictionary object will be instantiated and called as such:
# obj = MagicDictionary()
# obj.buildDict(dictionary)
# param_2 = obj.search(searchWord)
