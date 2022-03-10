class Trie:
    def __init__(self):
        self.children = [None] * 26
        self.is_end = False


class Solution:
    def findAllConcatenatedWordsInADict(self, words: List[str]) -> List[str]:
        trie = Trie()
        words.sort(key=lambda x: len(x))
        ans = []

        def insert(word):
            node = trie
            for c in word:
                idx = ord(c) - ord('a')
                if node.children[idx] is None:
                    node.children[idx] = Trie()
                node = node.children[idx]
            node.is_end = True

        def dfs(word):
            node = trie
            if not word:
                return True
            for i, c in enumerate(word):
                idx = ord(c) - ord('a')
                node = node.children[idx]
                if node is None:
                    return False
                if node.is_end:
                    if dfs(word[i + 1 :]):
                        return True
            return False

        for word in words:
            if not word:
                continue
            if dfs(word):
                ans.append(word)
            else:
                insert(word)
        return ans
