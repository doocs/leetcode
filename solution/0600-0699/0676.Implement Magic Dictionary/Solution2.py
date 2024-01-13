class Trie:
    __slots__ = ["children", "is_end"]

    def __init__(self):
        self.children: [Trie | None] = [None] * 26
        self.is_end = False

    def insert(self, w: str) -> None:
        node = self
        for c in w:
            idx = ord(c) - ord("a")
            if node.children[idx] is None:
                node.children[idx] = Trie()
            node = node.children[idx]
        node.is_end = True

    def search(self, w: str) -> bool:
        def dfs(i: int, node: [Trie | None], diff: int) -> bool:
            if i == len(w):
                return diff == 1 and node.is_end
            j = ord(w[i]) - ord("a")
            if node.children[j] and dfs(i + 1, node.children[j], diff):
                return True
            return diff == 0 and any(
                node.children[k] and dfs(i + 1, node.children[k], 1)
                for k in range(26)
                if k != j
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
