class Solution:
    def findRelativeRanks(self, score: List[int]) -> List[str]:
        n = len(score)
        idx = list(range(n))
        idx.sort(key=lambda x: -score[x])
        res = [None] * n
        for i in range(n):
            if i == 0:
                res[idx[i]] = 'Gold Medal'
            elif i == 1:
                res[idx[i]] = 'Silver Medal'
            elif i == 2:
                res[idx[i]] = 'Bronze Medal'
            else:
                res[idx[i]] = str(i + 1)
        return res
