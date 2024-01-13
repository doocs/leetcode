class Solution:
    def isCircularSentence(self, sentence: str) -> bool:
        ss = sentence.split()
        n = len(ss)
        return all(s[-1] == ss[(i + 1) % n][0] for i, s in enumerate(ss))
