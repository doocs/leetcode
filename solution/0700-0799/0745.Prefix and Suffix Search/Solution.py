class Trie:
    def __init__(self):
        self.children = [None] * 26
        self.indexes = []

    def insert(self, word, i):
        node = self
        for c in word:
            idx = ord(c) - ord("a")
            if node.children[idx] is None:
                node.children[idx] = Trie()
            node = node.children[idx]
            node.indexes.append(i)

    def search(self, pref):
        node = self
        for c in pref:
            idx = ord(c) - ord("a")
            if node.children[idx] is None:
                return []
            node = node.children[idx]
        return node.indexes


class WordFilter:
    def __init__(self, words: List[str]):
        self.p = Trie()
        self.s = Trie()
        for i, w in enumerate(words):
            self.p.insert(w, i)
            self.s.insert(w[::-1], i)

    def f(self, pref: str, suff: str) -> int:
        a = self.p.search(pref)
        b = self.s.search(suff[::-1])
        if not a or not b:
            return -1
        i, j = len(a) - 1, len(b) - 1
        while ~i and ~j:
            if a[i] == b[j]:
                return a[i]
            if a[i] > b[j]:
                i -= 1
            else:
                j -= 1
        return -1


# Your WordFilter object will be instantiated and called as such:
# obj = WordFilter(words)
# param_1 = obj.f(pref,suff)
