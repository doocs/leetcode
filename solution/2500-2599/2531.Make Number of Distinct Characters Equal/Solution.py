class Solution:
    def isItPossible(self, word1: str, word2: str) -> bool:
        cnt1 = Counter(word1)
        cnt2 = Counter(word2)
        x, y = len(cnt1), len(cnt2)
        for c1, v1 in cnt1.items():
            for c2, v2 in cnt2.items():
                if c1 == c2:
                    if x == y:
                        return True
                else:
                    a = x - (v1 == 1) + (cnt1[c2] == 0)
                    b = y - (v2 == 1) + (cnt2[c1] == 0)
                    if a == b:
                        return True
        return False
