class Solution:
    def replaceWords(self, dictionary: List[str], sentence: str) -> str:
        s = set(dictionary)
        words = sentence.split()
        for i, word in enumerate(words):
            for j in range(1, len(word) + 1):
                if word[:j] in s:
                    words[i] = word[:j]
                    break
        return ' '.join(words)
