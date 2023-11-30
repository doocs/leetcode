class Solution:
    def closeStrings(self, word1: str, word2: str) -> bool:
        cnt1 = Counter(word1)
        cnt2 = Counter(word2)
        return set(cnt1.keys()) == set(cnt2.keys()) and Counter(
            cnt1.values()
        ) == Counter(cnt2.values())
