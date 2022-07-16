class Trie:
    def __init__(self):
        self.children = [None] * 26
        self.v = defaultdict(int)

    def insert(self, w):
        node = self
        for c in w:
            idx = ord(c) - ord('a')
            if node.children[idx] is None:
                node.children[idx] = Trie()
            node = node.children[idx]
            node.v[(w[-1], len(w))] += 1

    def search(self, w):
        node = self
        res = []
        for c in w[:-1]:
            idx = ord(c) - ord('a')
            node = node.children[idx]
            res.append(c)
            if node.v[(w[-1], len(w))] == 1:
                break
        n = len(w) - len(res) - 1
        if n:
            res.append(str(n))
        res.append(w[-1])
        t = ''.join(res)
        return t if len(t) < len(w) else w


class Solution:
    def wordsAbbreviation(self, words: List[str]) -> List[str]:
        trie = Trie()
        for w in words:
            trie.insert(w)
        return [trie.search(w) for w in words]
