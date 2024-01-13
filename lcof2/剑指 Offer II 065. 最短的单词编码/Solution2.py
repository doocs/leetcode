class Trie:
    def __init__(self):
        self.children = [None] * 26

    def insert(self, w):
        node = self
        pref = True
        for c in w:
            idx = ord(c) - ord("a")
            if node.children[idx] is None:
                node.children[idx] = Trie()
                pref = False
            node = node.children[idx]
        return 0 if pref else len(w) + 1


class Solution:
    def minimumLengthEncoding(self, words: List[str]) -> int:
        words.sort(key=lambda x: -len(x))
        trie = Trie()
        return sum(trie.insert(w[::-1]) for w in words)
