class Solution:
    def halfQuestions(self, questions: List[int]) -> int:
        counter = Counter(questions)
        n = len(questions) >> 1
        ans = 0
        for i, v in counter.most_common():
            ans += 1
            if v >= n:
                return ans
            n -= v
        return ans
