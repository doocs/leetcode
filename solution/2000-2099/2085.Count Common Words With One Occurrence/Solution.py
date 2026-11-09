class Solution:
    def countWords(self, words1: list[str], words2: list[str]) -> int:
        cnt1 = Counter(words1)
        cnt2 = Counter(words2)
        return sum(v == 1 and cnt2[w] == 1 for w, v in cnt1.items())
