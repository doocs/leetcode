class MagicDictionary:
    def __init__(self):
        """
        Initialize your data structure here.
        """

    def _patterns(self, word):
        return [word[:i] + '*' + word[i + 1 :] for i in range(len(word))]

    def buildDict(self, dictionary: List[str]) -> None:
        self.words = set(dictionary)
        self.counter = Counter(p for word in dictionary for p in self._patterns(word))

    def search(self, searchWord: str) -> bool:
        for p in self._patterns(searchWord):
            if self.counter[p] > 1 or (
                self.counter[p] == 1 and searchWord not in self.words
            ):
                return True
        return False


# Your MagicDictionary object will be instantiated and called as such:
# obj = MagicDictionary()
# obj.buildDict(dictionary)
# param_2 = obj.search(searchWord)
