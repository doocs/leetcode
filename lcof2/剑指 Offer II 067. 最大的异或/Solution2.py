class Trie:
    def __init__(self):
        self.children = [None] * 2

    def insert(self, x):
        node = self
        for i in range(30, -1, -1):
            v = (x >> i) & 1
            if node.children[v] is None:
                node.children[v] = Trie()
            node = node.children[v]

    def search(self, x):
        node = self
        res = 0
        for i in range(30, -1, -1):
            v = (x >> i) & 1
            if node.children[v ^ 1]:
                res = res << 1 | 1
                node = node.children[v ^ 1]
            else:
                res <<= 1
                node = node.children[v]
        return res


class Solution:
    def findMaximumXOR(self, nums: List[int]) -> int:
        trie = Trie()
        for v in nums:
            trie.insert(v)
        return max(trie.search(v) for v in nums)
