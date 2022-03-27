class Solution:
    def highFive(self, items: List[List[int]]) -> List[List[int]]:
        s = [None] * 101
        for i, score in items:
            if s[i] is None:
                s[i] = []
            s[i].append(score)
        res = []
        for i, scores in enumerate(s):
            if scores is None:
                continue
            avg = sum(nlargest(5, scores)) // 5
            res.append([i, avg])
        return res
