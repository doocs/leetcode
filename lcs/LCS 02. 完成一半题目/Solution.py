class Solution:
    def halfQuestions(self, questions: List[int]) -> int:
        cnt = Counter(questions)
        ans, n = 0, len(questions) >> 1
        for _, v in cnt.most_common():
            ans += 1
            n -= v
            if n <= 0:
                break
        return ans
