class Solution:
    def maxConsecutiveAnswers(self, answerKey: str, k: int) -> int:
        def get(c, k):
            l = r = -1
            while r < len(answerKey) - 1:
                r += 1
                if answerKey[r] == c:
                    k -= 1
                if k < 0:
                    l += 1
                    if answerKey[l] == c:
                        k += 1
            return r - l

        return max(get('T', k), get('F', k))
