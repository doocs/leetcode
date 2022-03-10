class ValidWordAbbr:
    def __init__(self, dictionary: List[str]):
        self.words = defaultdict(set)
        for word in dictionary:
            abbr = self.word_abbr(word)
            self.words[abbr].add(word)

    def isUnique(self, word: str) -> bool:
        abbr = self.word_abbr(word)
        words = self.words[abbr]
        return not words or (len(words) == 1 and word in words)

    def word_abbr(self, s):
        return s if len(s) < 3 else f'{s[0]}{len(s) - 2}{s[-1]}'


# Your ValidWordAbbr object will be instantiated and called as such:
# obj = ValidWordAbbr(dictionary)
# param_1 = obj.isUnique(word)
