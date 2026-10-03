class Trie:
    def __init__(self):
        self.children: List[Optional[Trie]] = [None] * 26
        self.cost = inf

    def insert(self, word: str, cost: int):
        node = self
        for c in word:
            idx = ord(c) - ord("a")
            if node.children[idx] is None:
                node.children[idx] = Trie()
            node = node.children[idx]
        node.cost = min(node.cost, cost)


class Solution:
    def minimumCost(self, target: str, words: List[str], costs: List[int]) -> int:
        trie = Trie()
        for word, cost in zip(words, costs):
            trie.insert(word, cost)
        n = len(target)
        f = [inf] * (n + 1)
        f[n] = 0
        for i in range(n - 1, -1, -1):
            node = trie
            for j in range(i, n):
                idx = ord(target[j]) - ord("a")
                if node.children[idx] is None:
                    break
                node = node.children[idx]
                f[i] = min(f[i], node.cost + f[j + 1])
        return f[0] if f[0] < inf else -1
