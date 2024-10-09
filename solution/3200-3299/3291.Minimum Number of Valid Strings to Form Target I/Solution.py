def min(a: int, b: int) -> int:
    return a if a < b else b


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
        @cache
        def dfs(i: int) -> int:
            if i >= n:
                return 0
            node = trie
            ans = inf
            for j in range(i, n):
                k = ord(target[j]) - 97
                if node.children[k] is None:
                    break
                node = node.children[k]
                ans = min(ans, 1 + dfs(j + 1))
            return ans

        trie = Trie()
        for w in words:
            trie.insert(w)
        n = len(target)
        ans = dfs(0)
        return ans if ans < inf else -1
