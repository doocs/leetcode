class Solution:
    def isAlienSorted(self, words: List[str], order: str) -> bool:
        index = {c: i for i, c in enumerate(order)}
        for i in range(len(words) - 1):
            w1, w2 = words[i], words[i + 1]
            l1, l2 = len(w1), len(w2)
            flag = False
            for j in range(max(l1, l2)):
                i1, i2 = (
                    -1 if j >= l1 else index[w1[j]],
                    -1 if j >= l2 else index[w2[j]],
                )
                if i1 > i2:
                    return False
                if i1 < i2:
                    break
        return True
