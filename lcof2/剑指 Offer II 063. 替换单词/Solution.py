class Trie:
    def __init__(self):
        self.children = [None] * 26
        self.v = None

    def insert(self, word):
        node = self
        for c in word:
            idx = ord(c) - ord('a')
            if node.children[idx] is None:
                node.children[idx] = Trie()
            node = node.children[idx]
        node.v = word

    def search(self, word):
        node = self
        for c in word:
            idx = ord(c) - ord('a')
            if node.children[idx] is None:
                break
            node = node.children[idx]
            if node.v:
                return node.v
        return word


class Solution:
    def replaceWords(self, dictionary: List[str], sentence: str) -> str:
        trie = Trie()
        for v in dictionary:
            trie.insert(v)
        return ' '.join(trie.search(v) for v in sentence.split())
