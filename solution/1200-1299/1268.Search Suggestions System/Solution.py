class Trie:
    def __init__(self):
        self.children = [None] * 26
        self.v = []

    def insert(self, word, i):
        node = self
        for c in word:
            idx = ord(c) - ord('a')
            if node.children[idx] is None:
                node.children[idx] = Trie()
            node = node.children[idx]
            node.v.append(i)

    def search(self, word):
        res = [[] for _ in range(len(word))]
        node = self
        for i, c in enumerate(word):
            idx = ord(c) - ord('a')
            if node.children[idx] is None:
                break
            node = node.children[idx]
            res[i] = node.v[:3]
        return res


class Solution:
    def suggestedProducts(
        self, products: List[str], searchWord: str
    ) -> List[List[str]]:
        products.sort()
        trie = Trie()
        for i, w in enumerate(products):
            trie.insert(w, i)
        res = trie.search(searchWord)
        return [[products[j] for j in v] for v in res]
