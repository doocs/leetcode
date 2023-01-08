class Solution:
    def isItPossible(self, word1: str, word2: str) -> bool:
        cnt1 = [0] * 26
        cnt2 = [0] * 26
        for c in word1:
            cnt1[ord(c) - ord('a')] += 1
        for c in word2:
            cnt2[ord(c) - ord('a')] += 1
        for i, a in enumerate(cnt1):
            for j, b in enumerate(cnt2):
                if a and b:
                    cnt1[i], cnt2[j] = cnt1[i] - 1, cnt2[j] - 1
                    cnt1[j], cnt2[i] = cnt1[j] + 1, cnt2[i] + 1
                    if sum(v > 0 for v in cnt1) == sum(v > 0 for v in cnt2):
                        return True
                    cnt1[i], cnt2[j] = cnt1[i] + 1, cnt2[j] + 1
                    cnt1[j], cnt2[i] = cnt1[j] - 1, cnt2[i] - 1
        return False
