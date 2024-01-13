class Node:
    __slots__ = ['children', 'is_end']

    def __init__(self):
        self.children: List[Node | None] = [None] * 26
        self.is_end = False


class Solution:
    def minExtraChar(self, s: str, dictionary: List[str]) -> int:
        root = Node()
        for w in dictionary:
            node = root
            for c in w[::-1]:
                i = ord(c) - ord('a')
                if node.children[i] is None:
                    node.children[i] = Node()
                node = node.children[i]
            node.is_end = True

        n = len(s)
        f = [0] * (n + 1)
        for i in range(1, n + 1):
            f[i] = f[i - 1] + 1
            node = root
            for j in range(i - 1, -1, -1):
                node = node.children[ord(s[j]) - ord('a')]
                if node is None:
                    break
                if node.is_end and f[j] < f[i]:
                    f[i] = f[j]
        return f[n]
