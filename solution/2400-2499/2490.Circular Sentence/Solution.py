class Solution:
    def isCircularSentence(self, sentence: str) -> bool:
        sentence = sentence.split()
        return all(s[0] == sentence[i - 1][-1] for i, s in enumerate(sentence))
