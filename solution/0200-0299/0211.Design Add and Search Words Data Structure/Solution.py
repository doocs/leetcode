class Trie:

    def __init__(self):
        self.children = [None] * 26
        self.is_end = False


class WordDictionary:

    def __init__(self):
        """
        Initialize your data structure here.
        """
        self.trie = Trie()

    def addWord(self, word: str) -> None:
        node = self.trie
        for c in word:
            index = ord(c) - ord('a')
            if node.children[index] is None:
                node.children[index] = Trie()
            node = node.children[index]
        node.is_end = True

    def search(self, word: str) -> bool:
        return self._search(word, self.trie)

    def _search(self, word: str, node: Trie) -> bool:
        for i in range(len(word)):
            c = word[i]
            index = ord(c) - ord('a')
            if c != '.' and node.children[index] is None:
                return False
            if c == '.':
                for j in range(26):
                    if node.children[j] is not None and self._search(word[i + 1:], node.children[j]):
                        return True
                return False
            node = node.children[index]
        return node.is_end

# Your WordDictionary object will be instantiated and called as such:
# obj = WordDictionary()
# obj.addWord(word)
# param_2 = obj.search(word)
