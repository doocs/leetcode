class Solution:
    def validSequence(self, word1: str, word2: str) -> List[int]:
        m, n = len(word1), len(word2)
        suf = [0] * (m + 1)
        suf[m] = n
        j = n - 1
        for i in range(m - 1, -1, -1):
            if j >= 0 and word1[i] == word2[j]:
                j -= 1
            suf[i] = j + 1

        ans = []
        changed = False
        j = 0
        for i, c in enumerate(word1):
            if c == word2[j] or (not changed and suf[i + 1] <= j + 1):
                if c != word2[j]:
                    changed = True
                ans.append(i)
                j += 1
                if j == n:
                    return ans
        return []
