class Trie:
    __slots__ = ("children", "length", "idx")

    def __init__(self, length=inf, idx=inf):
        self.children = [None] * 26
        self.length = length
        self.idx = idx

    def insert(self, w: str, i: int):
        node = self
        for c in w:
            idx = ord(c) - ord("a")
            if not node.children[idx]:
                node.children[idx] = Trie()
            node = node.children[idx]
            if node.length > len(w):
                node.length = len(w)
                node.idx = i
            elif node.length == len(w):
                node.idx = min(node.idx, i)

    def query(self, w: str):
        node = self
        ans = node.idx
        for c in w:
            idx = ord(c) - ord("a")
            if not node.children[idx]:
                break
            node = node.children[idx]
            ans = node.idx
        return ans


class Solution:
    def stringIndices(
        self, wordsContainer: List[str], wordsQuery: List[str]
    ) -> List[int]:
        k = 0
        for i, w in enumerate(wordsContainer):
            if len(w) < len(wordsContainer[k]):
                k = i
        trie = Trie(len(wordsContainer[k]), k)
        for i, w in enumerate(wordsContainer):
            trie.insert(w[::-1], i)
        ans = []
        for i, w in enumerate(wordsQuery):
            ans.append(trie.query(w[::-1]))
        return ans
