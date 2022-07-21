class Trie:
    def __init__(self):
        self.children = [None] * 26
        self.is_end = False

    def insert(self, word):
        node = self
        for c in word:
            idx = ord(c) - ord('a')
            if node.children[idx] is None:
                node.children[idx] = Trie()
            node = node.children[idx]
        node.is_end = True

    def search(self, word):
        node = self
        for c in word:
            idx = ord(c) - ord('a')
            if node.children[idx] is None:
                return False
            node = node.children[idx]
        return node.is_end


class Solution:
    def longestWord(self, words: List[str]) -> str:
        def cmp(a, b):
            if len(a) != len(b):
                return len(a) - len(b)
            return -1 if a > b else 1

        def dfs(w):
            return not w or any(
                trie.search(w[:i]) and dfs(w[i:]) for i in range(1, len(w) + 1)
            )

        words.sort(key=cmp_to_key(cmp))
        trie = Trie()
        ans = ""
        for w in words:
            if dfs(w):
                ans = w
            trie.insert(w)
        return ans
