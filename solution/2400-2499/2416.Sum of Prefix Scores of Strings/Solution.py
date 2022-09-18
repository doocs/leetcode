class Trie:
    def __init__(self):
        self.children = [None] * 26
        self.cnt = 0

    def insert(self, w):
        node = self
        for c in w:
            idx = ord(c) - ord('a')
            if node.children[idx] is None:
                node.children[idx] = Trie()
            node = node.children[idx]
            node.cnt += 1

    def search(self, w):
        node = self
        ans = 0
        for c in w:
            idx = ord(c) - ord('a')
            if node.children[idx] is None:
                return ans
            node = node.children[idx]
            ans += node.cnt
        return ans


class Solution:
    def sumPrefixScores(self, words: List[str]) -> List[int]:
        trie = Trie()
        for w in words:
            trie.insert(w)
        return [trie.search(w) for w in words]
