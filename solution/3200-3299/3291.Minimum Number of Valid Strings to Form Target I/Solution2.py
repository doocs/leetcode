class Trie:
    def __init__(self):
        self.children: List[Optional[Trie]] = [None] * 26

    def insert(self, w: str):
        node = self
        for i in map(lambda c: ord(c) - 97, w):
            if node.children[i] is None:
                node.children[i] = Trie()
            node = node.children[i]


class Solution:
    def minValidStrings(self, words: List[str], target: str) -> int:
        trie = Trie()
        for w in words:
            trie.insert(w)
        n = len(target)
        f = [inf] * (n + 1)
        f[n] = 0
        for i in range(n - 1, -1, -1):
            node = trie
            for j in range(i, n):
                k = ord(target[j]) - 97
                if node.children[k] is None:
                    break
                node = node.children[k]
                f[i] = min(f[i], 1 + f[j + 1])
        return f[0] if f[0] < inf else -1
