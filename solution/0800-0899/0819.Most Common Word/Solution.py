class Solution:
    def mostCommonWord(self, paragraph: str, banned: List[str]) -> str:
        paragraph = collections.Counter(re.findall('[a-z]+', paragraph.lower()))
        banned_words = set(banned)
        for word, _ in paragraph.most_common():
            if word not in banned_words:
                return word
