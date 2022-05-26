class Trie:
    def __init__(self):
        self.children = [None] * 26
        self.count = 0
        self.pre_count = 0

    def insert(self, word: str) -> None:
        node = self
        for c in word:
            index = ord(c) - ord('a')
            if node.children[index] is None:
                node.children[index] = Trie()
            node = node.children[index]
            node.pre_count += 1
        node.count += 1

    def countWordsEqualTo(self, word: str) -> int:
        node = self._search_prefix(word)
        return 0 if node is None else node.count

    def countWordsStartingWith(self, prefix: str) -> int:
        node = self._search_prefix(prefix)
        return 0 if node is None else node.pre_count

    def erase(self, word: str) -> None:
        node = self
        for c in word:
            index = ord(c) - ord('a')
            node = node.children[index]
            node.pre_count -= 1
        node.count -= 1

    def _search_prefix(self, prefix: str):
        node = self
        for c in prefix:
            index = ord(c) - ord('a')
            if node.children[index] is None:
                return None
            node = node.children[index]
        return node


# Your Trie object will be instantiated and called as such:
# obj = Trie()
# obj.insert(word)
# param_2 = obj.countWordsEqualTo(word)
# param_3 = obj.countWordsStartingWith(prefix)
# obj.erase(word)
