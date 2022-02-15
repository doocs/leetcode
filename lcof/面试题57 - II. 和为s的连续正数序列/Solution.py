class Solution:
    def findContinuousSequence(self, target: int) -> List[List[int]]:
        res = []
        p, q = 1, 2
        while p < q:
            s = (p + q) * (q - p + 1) >> 1
            if s == target:
                res.append([i for i in range(p, q + 1)])
                p += 1
            elif s < target:
                q += 1
            else:
                p += 1
        return res
