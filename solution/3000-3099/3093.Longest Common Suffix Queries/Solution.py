class Trie:
    __slots__ = ("children", "length", "idx")

    def __init__(self):
        self.children = [None] * 26
        self.length = inf
        self.idx = inf

    def insert(self, w: str, i: int):
        node = self
        if node.length > len(w):
            node.length = len(w)
            node.idx = i
        for c in w[::-1]:
            idx = ord(c) - ord("a")
            if node.children[idx] is None:
                node.children[idx] = Trie()
            node = node.children[idx]
            if node.length > len(w):
                node.length = len(w)
                node.idx = i

    def query(self, w: str) -> int:
        node = self
        for c in w[::-1]:
            idx = ord(c) - ord("a")
            if node.children[idx] is None:
                break
            node = node.children[idx]
        return node.idx


class Solution:
    def stringIndices(
        self, wordsContainer: List[str], wordsQuery: List[str]
    ) -> List[int]:
        trie = Trie()
        for i, w in enumerate(wordsContainer):
            trie.insert(w, i)
        return [trie.query(w) for w in wordsQuery]
