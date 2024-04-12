class Solution:
    def maxConsecutiveAnswers(self, answerKey: str, k: int) -> int:
        def f(c: str) -> int:
            cnt = j = 0
            ans = 0
            for i, ch in enumerate(answerKey):
                cnt += ch == c
                while cnt > k:
                    cnt -= answerKey[j] == c
                    j += 1
                ans = max(ans, i - j + 1)
            return ans

        return max(f("T"), f("F"))
