class ValidWordAbbr:

    def __init__(self, dictionary: List[str]):
        self.words = {}
        for word in dictionary:
            abbr = self._word_abbr(word)
            vals = self.words.get(abbr, set())
            vals.add(word)
            self.words[abbr] = vals

    def isUnique(self, word: str) -> bool:
        abbr = self._word_abbr(word)
        vals = self.words.get(abbr)
        return vals is None or (len(vals) == 1 and word in vals)

    def _word_abbr(self, word: str) -> str:
        n = len(word)
        if n < 3:
            return word
        return f'{word[0]}{n - 2}{word[n - 1]}'


# Your ValidWordAbbr object will be instantiated and called as such:
# obj = ValidWordAbbr(dictionary)
# param_1 = obj.isUnique(word)
