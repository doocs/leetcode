class Solution:
    def halfQuestions(self, questions: List[int]) -> int:
        counter = collections.Counter(questions)
        counter = OrderedDict(counter.most_common())
        n = len(questions) >> 1
        res = 0
        for v in counter.values():
            res += 1
            if v >= n:
                return res
            n -= v
        return res
