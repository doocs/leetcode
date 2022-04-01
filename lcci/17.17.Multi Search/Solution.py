class Trie:
    def __init__(self):
        self.idx = -1
        self.children = [None] * 26

    def insert(self, word, i):
        node = self
        for c in word:
            idx = ord(c) - ord('a')
            if node.children[idx] is None:
                node.children[idx] = Trie()
            node = node.children[idx]
        node.idx = i

    def search(self, word):
        res = []
        node = self
        for c in word:
            idx = ord(c) - ord('a')
            if node.children[idx] is None:
                return res
            node = node.children[idx]
            if node.idx != -1:
                res.append(node.idx)
        return res


class Solution:
    def multiSearch(self, big: str, smalls: List[str]) -> List[List[int]]:
        tree = Trie()
        for i, s in enumerate(smalls):
            tree.insert(s, i)
        n = len(smalls)
        ans = [[] for _ in range(n)]
        for i in range(len(big)):
            s = big[i:]
            for idx in tree.search(s):
                ans[idx].append(i)
        return ans
